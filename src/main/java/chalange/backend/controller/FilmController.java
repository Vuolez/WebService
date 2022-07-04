package chalange.backend.controller;

import chalange.backend.dto.ImdbFilmDto;
import chalange.backend.dto.OmdbFilmDto;
import chalange.backend.entity.User;
import chalange.backend.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/films")
public class FilmController {
    private final FilmService filmService;

    @Value("${films.requestMinLen}")
    private int requestMinLen = 1; // todo: через @Value

    @GetMapping("/isBestPicture")
    public ResponseEntity<Boolean> isBestPicture(@RequestParam String name){ //todo: сделать валдацию через какуюто анатацию
        if (name.length() < requestMinLen) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(filmService.isBestPicture(name));
    }

    @GetMapping("/getBoxOfficeFilms")
    public ResponseEntity<List<ImdbFilmDto>> getBoxOfficeFilms(@RequestParam int count){
        return ResponseEntity.ok(filmService.getBoxOfficeFilms(count));
    }


}
