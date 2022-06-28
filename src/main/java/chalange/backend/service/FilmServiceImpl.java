package chalange.backend.service;

import chalange.backend.dto.FilmDto;
import chalange.backend.dto.PostDto;
import chalange.backend.repository.FilmRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;

@Service("filmService")
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService{
    private final FilmRepository filmRepository;
    private final WebClient webClient;
    private final ModelMapper modelMapper;

    private final String apikey = "7d0d331a";

    @Override
    public boolean isBestPicture(final String name) throws Exception {
        if(checkFilmExists(name)){
            return filmRepository.findByNominee(name).stream()
                    .anyMatch(i -> i.getWon());
        }
        else{
            throw new Exception("Film not found");
        }
    }

    private boolean checkFilmExists(final String name) throws JSONException {
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("t", name)
                        .queryParam("apikey", apikey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject json = new JSONObject(response);
        return json.getBoolean("Response");
    }
}
