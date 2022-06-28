package chalange.backend.controller;

import chalange.backend.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/films")
public class FilmController {
    private final FilmService filmService;

    @GetMapping // todo: добавить конкретный маппинг
    public ResponseEntity<Boolean> isBestPicture(@RequestParam String name) throws Exception { // todo: добавить валидацию (минимум символов), возвращать 400, если валидация нарушена
        return ResponseEntity.ok(filmService.isBestPicture(name));
    }

    @ExceptionHandler(Exception.class) // todo: перенести в класс c @ControllerAdvice, возвращать 500
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.ok(e.getMessage());
    }
}
