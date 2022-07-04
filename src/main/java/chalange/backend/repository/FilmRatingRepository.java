package chalange.backend.repository;

import chalange.backend.entity.FilmRating;
import chalange.backend.entity.pk.FilmRatingPk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRatingRepository extends JpaRepository<FilmRating, FilmRatingPk> {
    List<FilmRating> findAllByOrderByRatingDesc(Pageable limit);
}
