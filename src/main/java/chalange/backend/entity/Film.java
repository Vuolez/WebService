package chalange.backend.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "films")
public class Film extends BaseEntity {
    @Column(name = "year")
    private int year;

    @Column(name = "category")
    private String category;

    @Column(name = "nominee")
    private String nominee;

    @Column(name = "info")
    private String info;

    @Column(name = "won")
    private boolean isWon;

    @Column(name = "avg_rating")
    private Double averageRating;


    @Column(name = "ratings")
    @OneToMany(mappedBy = "film")
    private List<FilmRating> ratings = new ArrayList<>();

    public Boolean getIsWon() {
        return isWon;
    }

    public void addRating(@NonNull FilmRating filmRating) {
        ratings.add(filmRating);
        filmRating.setFilm(this);
    }
}
