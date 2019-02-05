package album.common.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "path")
    private String path;

    private User user;

    @ManyToOne
    @JoinColumn(name="photo_fk",insertable=false,updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Photo(){
        this.user = user;

    }

    public Photo(User user){
        this.user = user;

    }


    public Photo(String description, String path,User user) {
        this.description = description;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", user=" + user +
                '}';
    }
}

