package chalange.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmDto {
    private boolean Response;
    public boolean getResponse(){
        return Response;
    }

    public FilmDto(String Response){
        if(Response.equalsIgnoreCase("True"))
            this.Response = true;
    }
}
