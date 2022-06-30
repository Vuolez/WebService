package chalange.backend.service;

import chalange.backend.exception.FilmNotFoundException;

public interface FilmService {
    boolean isBestPicture(final String name) throws FilmNotFoundException;
}
