package chalange.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(name = "ratings")
    @OneToMany(mappedBy = "film")
    private List<FilmRating> ratings = new ArrayList<>();

    public Boolean getIsWon() {
        return isWon;
    }

    public Double getAverageRating(){
        double sum = ratings.stream().mapToInt(i -> i.getRating()).sum();
        return sum / ratings.size();
    }
}
