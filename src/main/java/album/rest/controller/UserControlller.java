package album.rest.controller;

import album.backend.UserRepository;
import album.common.domain.Photo;
import album.common.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserControlller {
    UserRepository userRepository = new UserRepository();
    @GetMapping("/getAll")
    public String get() {
        return "Get all is working";
    }

    @GetMapping("/get/{id}")
    public String getUserById(@PathVariable Integer id) {
        return String.valueOf(id);
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user){
        userRepository.saveUser(user);
        return "saved user " + user.toString();
    }

}
