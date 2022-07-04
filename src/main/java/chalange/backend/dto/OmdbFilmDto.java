package chalange.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OmdbFilmDto {
    @JsonAlias(value = {"Response"})
    private boolean isSuccessfulResponse;

    @JsonAlias(value = {"BoxOffice"})
    private String boxOffice;
}