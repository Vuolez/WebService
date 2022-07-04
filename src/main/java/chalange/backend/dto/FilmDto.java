package chalange.backend.dto;

import chalange.backend.entity.FilmRating;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
public class FilmDto {
    private Long id;
    private String category;
    private String nominee;
    private String info;
    private Boolean isWon;
    private Double averageRating;
}
