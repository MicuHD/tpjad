package album.backend;

import album.HibernateUtil;
import album.common.domain.User;
import org.hibernate.Session;

public class UserRepository {
    public UserRepository(){

    }

    public Integer saveUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer id = (Integer) session.save(user);
        session.close();
        return id;
    }

    public User findById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.getReference(User.class,id);
        session.close();
        return user;
    }





}
