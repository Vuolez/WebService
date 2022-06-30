package chalange.backend.service;

import chalange.backend.dto.external.OmdbApiResponse;
import chalange.backend.exception.FilmNotFoundException;
import chalange.backend.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service("filmService")
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final WebClient webClient;

    @Value("${omdbapi.apikey}")
    private String apikey; // todo: перенести в конфиг, добавить в системные переменные (не может быть финальным)
    private final String tParam = "t"; // todo: синтаксис для констант
    private final String apikeyParam = "apikey";


    @Override
    public boolean isBestPicture(final String name) throws FilmNotFoundException {
        if (checkFilmExists(name)) {
            return filmRepository.existsByNomineeAndIsWonTrue(name);
        } else {
            throw new FilmNotFoundException("Film not found");
        }
    }

    private boolean checkFilmExists(final String name) {
        var response = Optional.ofNullable(webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/") // todo: константа
                        .queryParam(tParam, name)
                        .queryParam(apikeyParam, apikey)
                        .build())
                .retrieve()
                .bodyToMono(OmdbApiResponse.class)
                .block());
        return response.map(OmdbApiResponse::isSuccessfulResponse).orElse(false);
    }
}
