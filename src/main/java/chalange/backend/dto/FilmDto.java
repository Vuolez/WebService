package chalange.backend.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class FilmDto {
    private boolean response;
    public boolean getResponse(){
        return response;
    }
}
