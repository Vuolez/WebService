package chalange.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;
}
