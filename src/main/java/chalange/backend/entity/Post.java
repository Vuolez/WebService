package chalange.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User UserId;

    @ManyToMany
    @JoinTable(
            name = "posts_tags",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;
}
