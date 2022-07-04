package chalange.backend.entity.pk;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class FilmRatingPk implements Serializable {
    @Column(name = "user_id")
    private Long user;
    @Column(name = "film_id")
    private Long film;

    public FilmRatingPk(Long user, Long film) {
        this.user = user;
        this.film = film;
    }
}
