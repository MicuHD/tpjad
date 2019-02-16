package album.rest.controller;

import album.backend.PhotoDAO;
import album.backend.UserDAO;
import album.common.domain.Photo;
import album.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/user")
public class UserControlller {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PhotoDAO photoDAO;

    @PostMapping("/register")
    public ResponseEntity add(@RequestBody User user) {

        try {
            System.out.println(user.getUsername());
            userDAO.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            if (user.getId() != null) {
                userDAO.delete(user.getId());
            }
            System.out.println("ERR Message: " + e.getMessage());
            if (e.getMessage().contains("ConstraintViolationException")) {
                return ResponseEntity.badRequest().body("Username-ul este deja folosit, va rugam sa folositi altul!");
            }
            return ResponseEntity.badRequest().build();

        }

        return ResponseEntity.ok("{}");

    }

    private User checkUser(User user){
        Iterable<User> iterable = userDAO.findAll();

        for(User us : iterable){
            System.out.println("Comparing" + us.getUsername() + " " + us.getPassword());
            if(us.getUsername().equals(user.getUsername())){
                if(us.getPassword().equals(user.getPassword())){
                    return us;
                }
            }
        }

        return null;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user, HttpSession session) {
        User saveduser = checkUser(user);
        if (saveduser != null) {
            String symbols = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
            String sessionID = "";
            Random rand = new Random();
            for (int i = 0; i < 20; i++) {
                sessionID += symbols.charAt(rand.nextInt(symbols.length()));
            }

//            TODO: Delete user after front end changes to sessionID
            session.setAttribute("user", user.getUsername());
            session.setAttribute("sessionId", sessionID);
            session.setAttribute("userId",String.valueOf(saveduser.getId()));

            //saveduser.setSessionID(sessionID);
            saveduser.setSessionID(sessionID);
            userDAO.save(saveduser);


            List<String> sessionAttributes = Collections.list(session.getAttributeNames());
            for (String s : sessionAttributes) {
                if (s.contains("FailCounter"))
                    session.removeAttribute(s);
            }

            return ResponseEntity.ok("{}");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable int id, HttpSession session) {
        System.out.println("get User");
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            return userDAO.findOne(Long.parseLong(userId));
        }
        return null;
    }

    @GetMapping("/logged")
    public int logged(HttpSession session) {
        System.out.println("get logged");
        System.out.println(session.getAttribute("userId") + " " + session.getAttribute("user") +  session.getAttribute("sessionId"));
        if (session.getAttribute("sessionId") != null) {
            return userDAO.findOne(Long.parseLong((String)session.getAttribute("userId"))).getId().intValue();
        }
        return -1;
    }


    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId != null) {
            //            TODO: Delete user after front end changes to sessionID
            session.setAttribute("user", null);
            session.setAttribute("sessionId", null);
            Long id = Long.parseLong((String)session.getAttribute("userId"));
            session.setAttribute("userId",null);
            User user = userDAO.findOne(id);
            user.setSessionID(null);
            userDAO.save(user);
            return ResponseEntity.ok("{}");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/saveImage")
    public ResponseEntity saveImage(@RequestParam("file")MultipartFile file, HttpSession session){
        System.out.println(session.getAttribute("userId") + " " + session.getAttribute("user") +  session.getAttribute("sessionId"));
        if(session.getAttribute("userId") == null){
            return ResponseEntity.badRequest().build();
        }
        if(file != null){
            String[] tokens = file.getOriginalFilename().split("\\.");
            String extension = tokens[tokens.length-1];

            User user = userDAO.findOne(Long.parseLong((String)session.getAttribute("userId")));
            Photo photo = new Photo("descriere interesanta",user);

            user.addPhoto(photo);
            List<Photo> photos = user.getPhotos();
            userDAO.save(user);

            Photo photo1 = photos.get(photos.size()-1);
            String name = user.getUsername() + String.valueOf(photo1.getId());
            String path = name + "." + extension;

            userDAO.save(user);

            System.out.println(path);
            File convFile = new File("images/" +path);
            System.out.println(convFile.getAbsolutePath());
            FileOutputStream fos = null;
            try {
                convFile.createNewFile();
                fos = new FileOutputStream(convFile);
                fos.write(file.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ;

            return ResponseEntity.ok("{}");
        }
        return ResponseEntity.badRequest().build();
    }

}
