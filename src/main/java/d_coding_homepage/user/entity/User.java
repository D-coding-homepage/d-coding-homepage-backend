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

    @Id
    @GeneratedValue
    private String id;

    private String password;

    private String name;

    private String phoneNumber;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<StudyUser> studyUsers = new ArrayList();;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<UserRole> roles = new ArrayList();
}
