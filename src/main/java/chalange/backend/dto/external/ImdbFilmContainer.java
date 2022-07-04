package chalange.backend.dto.external;

import chalange.backend.dto.ImdbFilmDto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImdbFilmContainer {

    @JsonAlias(value = {"items"})
    private List<ImdbFilmDto> items;

    @JsonAlias(value = {"errorMessage"})
    private String errorMessage;
}
