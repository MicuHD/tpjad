package album.rest.controller;

import album.backend.PhotoDAO;
import album.common.domain.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/photos")
public class PhotoControler {
    @Autowired
    PhotoDAO photoDAO;

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
            photoDAO.save(image);
      //  else
       //     throw new Exception("User id doesn't exist");
    }
}
