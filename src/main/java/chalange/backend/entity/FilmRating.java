package chalange.backend.entity;

import chalange.backend.entity.pk.FilmRatingPk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "film_rating")
public class FilmRating implements Serializable {
    @EmbeddedId
    private FilmRatingPk filmRatingPk;

    @Column(name = "rating")
    private int rating;


    public FilmRating(){
    };
    public FilmRating(FilmRatingPk filmRatingPk, int rating) {
        this.filmRatingPk = filmRatingPk;
        this.rating = rating;
    }
}
