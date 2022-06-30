package chalange.backend.service;

import chalange.backend.dto.FilmDto;
import chalange.backend.exception.FilmNotFoundException;
import chalange.backend.repository.FilmRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service("filmService")
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final WebClient webClient;

    @Value("${omdbapi.apikey}")
    private final String apikey; // todo: перенести в конфиг, добавить в системные переменные
    private final String tParam = "t";
    private final String apikeyParam = "apikey";


    @Override
    public boolean isBestPicture(final String name) throws FilmNotFoundException {
        if (checkFilmExists(name)) {
            return !filmRepository.findIfWon(name).isEmpty();
        } else {
            throw new FilmNotFoundException("Film not found");
        }
    }

    private boolean checkFilmExists(final String name){
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam(tParam, name)
                        .queryParam(apikeyParam, apikey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JSONObject json = new JSONObject(response); // todo: заменить на ДТО (object Mapper?)

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            //FilmDto filmDto = objectMapper.readValue(response, FilmDto.class);
            FilmDto filmDto = new FilmDto(jsonNode.get("Response").toString());

            return filmDto.getResponse();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
