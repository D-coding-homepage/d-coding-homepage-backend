package d_coding_homepage.study.entity;

import d_coding_homepage.global.entity.BaseEntity;
import d_coding_homepage.studyuser.StudyUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studies")
public class Study extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String studyName;
    private String category;

    @Lob
    private String content;

    @OneToMany(mappedBy = "study")
    private List<StudyUser> studyUsers = new ArrayList();
}
