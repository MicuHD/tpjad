package album;

import album.backend.PhotoDAO;
import album.backend.UserDAO;
import album.common.domain.Photo;
import album.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit implements ApplicationRunner {

    private PhotoDAO photoDAO;
    private UserDAO userDAO;

    @Autowired
    public DataInit(UserDAO userDAO, PhotoDAO photoDAO){
        this.userDAO = userDAO;
        this.photoDAO = photoDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("all good");
        long count = userDAO.count();
        if (count == 0) {
            User user = new User();

            user.setUsername("Johnny");
            user.setPassword("test");

            Photo im1 = new Photo("test",user);
            Photo im2 = new Photo("tes2",user);
            List<Photo> images = new ArrayList<>();
            images.add(im1); images.add(im2);

            user.setPhotos(images);

            userDAO.save(user);

            Photo im3 = new Photo("test3",user);
            user.addPhoto(im3);

            userDAO.save(user);

//            photoDAO.save(im1);
//            photoDAO.save(im2);

        }
    }
}
