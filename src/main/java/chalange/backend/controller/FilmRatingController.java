package chalange.backend.controller;

import chalange.backend.dto.FilmDto;
import chalange.backend.entity.Film;
import chalange.backend.entity.User;
import chalange.backend.service.FilmRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/film_rating")
public class FilmRatingController {
    private final FilmRatingService filmRatingService;

    @GetMapping("/setRating")
    public void setRating(@AuthenticationPrincipal User user, @RequestParam Long filmId, @RequestParam int rating){
        filmRatingService.setRating(user,filmId, rating);
    }

    @GetMapping("/getTopRatedMovies")
    public ResponseEntity<List<FilmDto>> getTopRatedMovies(@RequestParam int count){
        return ResponseEntity.ok(filmRatingService.getTopRatedMovies(count));
    }
}
