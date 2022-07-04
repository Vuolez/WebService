package chalange.backend.entity.pk;

import chalange.backend.entity.Film;
import chalange.backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class FilmRatingPk implements Serializable {
    private User user;
    private Film film;

    public FilmRatingPk(User user, Film film) {
        this.user = user;
        this.film = film;
    }
}
