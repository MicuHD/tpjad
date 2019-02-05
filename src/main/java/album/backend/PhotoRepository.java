package album.backend;

import album.HibernateUtil;
import album.common.domain.Photo;
import org.hibernate.Session;

public class PhotoRepository {

    public PhotoRepository(){

    }

    public Integer savePhoto(Photo photo){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer id = (Integer) session.save(photo);
        session.close();
        return id;
    }
}
