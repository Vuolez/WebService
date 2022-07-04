package chalange.backend.service;

import chalange.backend.entity.FilmRating;

import java.util.List;

public interface FilmRatingService {
    void setRating(Long userId, Long filmId, int rating);
    List<FilmRating> getTopRatedMovies(int count);
}
