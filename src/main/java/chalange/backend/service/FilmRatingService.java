package chalange.backend.service;

import chalange.backend.dto.FilmDto;
import chalange.backend.entity.Film;
import chalange.backend.entity.FilmRating;
import chalange.backend.entity.User;

import java.util.List;

public interface FilmRatingService {
    void setRating(User user, Long filmId, int rating);
    List<FilmDto> getTopRatedMovies(int count);
}
