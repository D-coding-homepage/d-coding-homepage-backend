package d_coding_homepage.user.entity;

import d_coding_homepage.study.entity.Study;
import d_coding_homepage.studyuser.StudyUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    private String password;

    private String name;

    private String phoneNumber;
    @Id
    private String email;

    @OneToMany(mappedBy = "user")
    private List<StudyUser> studyUsers = new ArrayList();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserRole> roles = new ArrayList();

    public User(String email, String password, String name, String phoneNumber){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void addRole(UserRole userRole) {
        this.roles.add(userRole);
    }
}
