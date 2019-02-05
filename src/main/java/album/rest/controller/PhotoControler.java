package album.rest.controller;

import album.backend.PhotoRepository;
import album.backend.UserRepository;
import album.common.domain.Photo;
import album.common.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/photos")
public class PhotoControler {
    private PhotoRepository photoRepository = new PhotoRepository();
    private UserRepository userRepository = new UserRepository();

    @GetMapping("/getAll")
    public String get() {
        return "Get all is working";
    }

    @GetMapping("/get/category/{category}")
    public String getCategory(@PathVariable String category) {
        return "Category: " + category;
    }

    @PostMapping("/save")
    public void saveImage(@RequestBody Photo image) throws Exception {
        System.out.println(image.toString());

//        User user = userRepository.findById(image.getUser().getId());
  //      System.out.println(user.toString());
    //    if(user != null)
            photoRepository.savePhoto(image);
      //  else
       //     throw new Exception("User id doesn't exist");
    }
}
