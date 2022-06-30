package chalange.backend.controller;

import chalange.backend.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/films")
public class FilmController {
    private final FilmService filmService;
    private final int reqestMinLen = 1;

    @GetMapping("/isBestPicture")
    public ResponseEntity<Boolean> isBestPicture(@RequestParam String name) throws Exception {
        if (name.length() < reqestMinLen) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(filmService.isBestPicture(name));
    }
}
