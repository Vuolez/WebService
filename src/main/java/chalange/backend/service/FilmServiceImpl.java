package chalange.backend.service;

import chalange.backend.dto.ImdbFilmDto;
import chalange.backend.dto.OmdbFilmDto;
import chalange.backend.dto.external.ImdbFilmContainer;
import chalange.backend.dto.external.OmdbApiResponse;
import chalange.backend.exception.FilmNotFoundException;
import chalange.backend.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("filmService")
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final WebClient webClient;

    private static final String OMDB_BASE_URL = "https://www.omdbapi.com";
    private static final String IMDB_BASE_URL = "https://imdb-api.com/en/API/BoxOfficeAllTime/";
    private static final String T_P = "t";
    private static final String APIKEY_P = "apikey";
    private static final String SLASH = "/";

    @Value("${omdb.apikey}")
    private String omdbApikey;

    @Value("${imdb.apikey}")
    private String imdbApikey;

    @Override
    public boolean isBestPicture(final String name) throws FilmNotFoundException {
        if (checkFilmExists(name)) {
            return filmRepository.existsByNomineeAndIsWonTrue(name);
        } else {
            throw new FilmNotFoundException("Film not found");
        }
    }

    @Override
    public List<ImdbFilmDto> getBoxOfficeFilms(int count) {
        Optional<ImdbFilmContainer> response = Optional.ofNullable(webClient.get()
                .uri(IMDB_BASE_URL, uriBuilder -> uriBuilder
                        .path(SLASH+imdbApikey)
                        .build())
                .retrieve()
                .bodyToMono(ImdbFilmContainer.class)
                .block());

        List<Integer> ss;
        return response.get().getItems().stream().limit(count).collect(Collectors.toList());
    }

    private boolean checkFilmExists(final String name) {
        var response = Optional.ofNullable(webClient.get()
                .uri(OMDB_BASE_URL, uriBuilder -> uriBuilder
                        .path(SLASH)
                        .queryParam(T_P, name)
                        .queryParam(APIKEY_P, omdbApikey)
                        .build())
                .retrieve()
                .bodyToMono(OmdbApiResponse.class)
                .block());
        return response.map(OmdbApiResponse::isSuccessfulResponse).orElse(false);
    }
}
