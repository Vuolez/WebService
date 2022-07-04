package chalange.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "film_rating")
@NoArgsConstructor
public class FilmRating extends BaseEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "rating")
    private int rating;

    public FilmRating(User user, Film film, int rating){
        this.user = user;
        this.film = film;
        this.rating = rating;
    };
}
