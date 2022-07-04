package chalange.backend.service;

import chalange.backend.dto.FilmDto;
import chalange.backend.dto.UserDto;
import chalange.backend.entity.Film;
import chalange.backend.entity.FilmRating;
import chalange.backend.entity.User;
import chalange.backend.entity.pk.FilmRatingPk;
import chalange.backend.repository.FilmRatingRepository;
import chalange.backend.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("filmRatingService")
@RequiredArgsConstructor
public class FilmRatingServiceImpl implements FilmRatingService{

    private final FilmRatingRepository filmRatingRepository;
    private final FilmRepository filmRepository;
    private final ModelMapper modelMapper;

    @Override
    public void setRating(User user, Long filmId, int rating) {
        Optional<Film> film = filmRepository.findById(filmId);
        FilmRating filmRating = new FilmRating(user, film.get() , rating);
        filmRatingRepository.save(filmRating);
    }

    @Override
    public List<FilmDto> getTopRatedMovies(int count) {
        Pageable limit = PageRequest.of(0,count);
        List<Film> films = filmRepository.findAllByOrderByFilmRatingsDesc(limit);

        List<FilmDto> filmDtos = new ArrayList<>();
        for(Film film : films){
            FilmDto filmDto = modelMapper.map(film, FilmDto.class);

            if(film.getRatings().size() > 0){
                filmDto.setAverageRating(film.getAverageRating());
            }

            filmDtos.add(filmDto);
        }

        filmDtos.sort(Comparator.comparing(FilmDto::getAverageRating).reversed());

        return filmDtos;
    }


}
