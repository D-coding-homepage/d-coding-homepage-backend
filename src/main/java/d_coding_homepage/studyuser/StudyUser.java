package d_coding_homepage.studyuser;

import d_coding_homepage.global.entity.BaseEntity;
import d_coding_homepage.study.entity.Study;
import d_coding_homepage.user.entity.User;

import javax.persistence.*;


@Entity
@Table(name = "study_users")
public class StudyUser extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    //TODO Enum으로 변경
    private String role;
}
