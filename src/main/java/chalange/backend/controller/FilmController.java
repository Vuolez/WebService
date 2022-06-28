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

    @GetMapping
    public ResponseEntity<Boolean> isBestPicture(@RequestParam String name) throws Exception {
        return ResponseEntity.ok(filmService.isBestPicture(name));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.ok(e.getMessage());
    }
}
