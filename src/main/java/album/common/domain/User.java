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
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="passport",nullable = true)
    private String passportNumber;

    @Column
    @OneToMany(mappedBy = "user1",cascade=CascadeType.ALL)
    private List<Photo> photos;

    public User() {
        super();
    }

    public User(Long id, String name, String passportNumber) {
        super();
        this.id = id;
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public User(String name, String passportNumber) {
        super();
        this.name = name;
        this.passportNumber = passportNumber;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", photos=" + photos +
                '}';
    }

    public void addPhoto(Photo photo){
//        System.out.print(photo.toString());
        System.out.println(photo.getDescription() + " " + photo.getUser1().getId());
        //photo.setUser1(this);
        this.photos.add(photo);
    }
}
