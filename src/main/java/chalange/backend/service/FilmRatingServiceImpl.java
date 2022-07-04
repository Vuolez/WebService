package chalange.backend.service;

import chalange.backend.entity.FilmRating;
import chalange.backend.entity.pk.FilmRatingPk;
import chalange.backend.repository.FilmRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("filmRatingService")
@RequiredArgsConstructor
public class FilmRatingServiceImpl implements FilmRatingService{

    private final FilmRatingRepository filmRatingRepository;

    @Override
    public void setRating(Long userId, Long filmId, int rating) {
        FilmRating filmRating = new FilmRating(new FilmRatingPk(userId, filmId) , rating);
        filmRatingRepository.save(filmRating);
    }

    @Override
    public List<FilmRating> getTopRatedMovies(int count) {
        Pageable limit = PageRequest.of(0,count);
        return filmRatingRepository.findAllByOrderByRatingDesc(limit);
    }
}
