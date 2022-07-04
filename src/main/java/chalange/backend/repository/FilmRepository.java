package chalange.backend.repository;

import chalange.backend.entity.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    boolean existsByNomineeAndIsWonTrue(String name);

    @EntityGraph(attributePaths = {"ratings"})
    @Query("SELECT f FROM Film f")
    List<Film> findAllByOrderByFilmRatingsDesc(Pageable limit);
}
