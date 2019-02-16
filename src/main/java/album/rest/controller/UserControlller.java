package album.rest.controller;

import album.backend.PhotoDAO;
import album.backend.UserDAO;
import album.common.domain.Photo;
import album.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
