package chalange.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class RatingException extends RuntimeException {

    public RatingException(String message) {
        super(message);

    }

}
