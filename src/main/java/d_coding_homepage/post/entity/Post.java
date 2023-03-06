package d_coding_homepage.post.entity;


import d_coding_homepage.global.entity.BaseEntity;
import d_coding_homepage.todo.entity.Todo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Lob
    private String content;

    @OneToMany(mappedBy = "post")
    private List<File> files;

    @OneToMany(mappedBy = "post")
    private List<Todo> todos;
}
