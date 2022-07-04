package chalange.backend.service;

import chalange.backend.dto.ImdbFilmDto;
import chalange.backend.exception.FilmNotFoundException;

import java.util.List;

public interface FilmService {
    boolean isBestPicture(final String name) throws FilmNotFoundException;
    List<ImdbFilmDto> getBoxOfficeFilms(int count);
}
