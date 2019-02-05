package album.common.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",updatable = false,nullable = false)
    Integer id;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @OneToMany
    @JoinColumn(name="photo_fk")
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    List<Photo> photos = new ArrayList<>();

    /**
     sessionID - String
     ID-ul unic al sesiunii curente
     */
    @Column(name = "sessionId")
    String sessionID;

    /**
     * Constructor default
     */
    public User() {

    }

    public User(Integer id){
        this.id = id;
    }
    /**
     * Constructor cu parametrii
     * @param email
     * @param passwd
     */
    public User(String email, String passwd) {
        this.email = email;
        this.password = passwd;
    }

    ////////////////////////////getters and setters start///////////////////////////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photos=" + photos +
                ", sessionID='" + sessionID + '\'' +
                '}';
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    ////////////////////////////getters and setters end///////////////////////////////
}

