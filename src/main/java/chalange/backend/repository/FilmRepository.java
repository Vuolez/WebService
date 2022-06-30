package chalange.backend.repository;

import chalange.backend.entity.Film;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByNominee(final String name);

    @Query("SELECT f FROM Film f WHERE f.nominee=:name AND f.isWon = true")
    List<Film> findIfWon(final String name);
}
