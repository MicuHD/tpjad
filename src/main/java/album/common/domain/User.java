package album.common.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue
    @Column(name="user_id",nullable=false)
    private Long id;
    @Column(name="username",nullable = false,unique = true)
    private String username;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photos=" + photos +
                ", sessionID='" + sessionID + '\'' +
                '}';
    }

    @Column(name="password",nullable = false)
    private String password;

    @Column
    @OneToMany(mappedBy = "user1",cascade=CascadeType.ALL)
    private List<Photo> photos;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    @Column(name = "sessionId",nullable = true)
    String sessionID;

    public User() {
        super();
    }

    public User(Long id, String name, String password) {
        super();
        this.id = id;
        this.username = name;
        this.password = password;
    }

    public User(String username, String password, String sessionID) {
        this.username = username;
        this.password = password;
        this.sessionID = sessionID;
    }

    public User(String name, String password) {
        super();
        this.username = name;
        this.password = password;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }



    public void addPhoto(Photo photo){
//        System.out.print(photo.toString());
        System.out.println(photo.getDescription() + " " + photo.getUser1().getId());
        //photo.setUser1(this);
        this.photos.add(photo);
    }
}
