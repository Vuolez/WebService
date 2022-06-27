package chalange.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;
}
