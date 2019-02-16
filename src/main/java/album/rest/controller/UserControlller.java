package album.rest.controller;

import album.backend.PhotoDAO;
import album.backend.UserDAO;
import album.common.domain.Photo;
import album.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControlller {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PhotoDAO photoDAO;

    @GetMapping("/Users")
    public String retrieveAllUsers() {
        Iterable<User> all = userDAO.findAll();

        StringBuilder sb = new StringBuilder();

        all.forEach(p -> sb.append(p.getName() + "<br>"));

        return sb.toString();
    }

    @GetMapping("/Users/{id}")
    public User retrieveUser(@PathVariable long id) throws Exception {
        return null;
    }

    @DeleteMapping("/Users/{id}")
    public void deleteUser(@PathVariable long id) {

    }

    @PostMapping("/Users/Photo/{photo}")
    public ResponseEntity<Object> createUser(@RequestBody Photo photo) {
        return null;
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User User, @PathVariable long id) {
        return null;
    }
}
