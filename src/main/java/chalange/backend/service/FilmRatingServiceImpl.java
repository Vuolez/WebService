package chalange.backend.service;

import chalange.backend.dto.FilmDto;
import chalange.backend.entity.Film;
import chalange.backend.entity.FilmRating;
import chalange.backend.entity.User;
import chalange.backend.exception.FilmNotFoundException;
import chalange.backend.exception.RatingException;
import chalange.backend.repository.FilmRatingRepository;
import chalange.backend.repository.FilmRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("filmRatingService")
@RequiredArgsConstructor
public class FilmRatingServiceImpl implements FilmRatingService {

    private final FilmRatingRepository filmRatingRepository;
    private final FilmRepository filmRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void setRating(User user, Long filmId, int rating) {
        Film film = filmRepository.findById(filmId).orElseThrow(() ->
                new FilmNotFoundException(String.format("Film with id - %s was not found", filmId)));
        if (film.getRatings().stream().anyMatch(r -> user.getId().equals(r.getUser().getId()))) {
            throw new RatingException("User already submitted rating for the film");
        }
        FilmRating filmRating = new FilmRating();
        filmRating.setRating(rating);
        filmRating.setUser(user);
        film.addRating(filmRating);
        // calculate average film rating after adding it to the film
        film.setAverageRating(getAverageFilmRating(film));
        filmRatingRepository.save(filmRating);
    }

    @Override
    public List<FilmDto> getTopRatedMovies(int count) {
        Pageable limit = PageRequest.of(0, count, Sort.by("averageRating").descending());
        List<Film> films = filmRepository.findAllByAndAverageRatingIsNotNull(limit);

        return films.stream()
                .map(film -> modelMapper.map(film, FilmDto.class))
                .collect(Collectors.toList());
    }

    private Double getAverageFilmRating(@NonNull Film film) {
        List<FilmRating> ratings = Optional.ofNullable(film.getRatings()).orElseThrow(() ->
                new RuntimeException(String.format("Ratings not found for film with id = %s", film.getId())));
        double sum = ratings.stream().mapToInt(FilmRating::getRating).sum();
        return sum / ratings.size();
    }
}
