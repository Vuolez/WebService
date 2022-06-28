package chalange.backend.service;

import chalange.backend.entity.Film;
import chalange.backend.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("filmService")
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final WebClient webClient;

    private final String apikey = "7d0d331a"; // todo: перенести в конфиг, добавить в системные переменные

    @Override
    public boolean isBestPicture(final String name) throws Exception {
        if (checkFilmExists(name)) {
            return filmRepository.findByNominee(name).stream() // todo: заменить на квери, которая это проверит
                    .anyMatch(Film::getIsWon);
        } else {
            throw new Exception("Film not found"); // todo: сделать кастомный (свой) exception
        }
    }

    private boolean checkFilmExists(final String name) throws JSONException { // todo: избавиться от конструкции throws
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder // todo: вынести все строки в константы
                        .path("/")
                        .queryParam("t", name)
                        .queryParam("apikey", apikey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject json = new JSONObject(response); // todo: заменить на ДТО (object Mapper?)
        return json.getBoolean("Response");
    }
}
