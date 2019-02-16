package album.common.domain;

import javax.persistence.*;

@Entity
@Table(name = "Photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photoId")
    private long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user1;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "path")
    private String path;

    public Photo(){

    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", user1=" + user1 +
                ", path='" + path + '\'' +
                '}';
    }

    public Photo(String description, User user1) {
        this.description = description;
        this.user1 = user1;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }
}
