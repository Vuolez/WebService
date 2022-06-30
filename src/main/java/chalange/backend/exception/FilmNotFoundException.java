package chalange.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FilmNotFoundException extends Exception {
    public FilmNotFoundException(String message) {
        super(message);
    }
}

