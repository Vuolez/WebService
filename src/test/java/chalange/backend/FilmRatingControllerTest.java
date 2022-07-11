package chalange.backend;

import chalange.backend.dto.FilmDto;
import chalange.backend.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class FilmRatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testTest() throws Exception {
        mockMvc.perform(get("/api/users/test")).andExpect(status().is(200));
    }

    private FilmDtos jsonToFilmDtos(String jsonContent) {
        FilmDtos filmDtos = new FilmDtos();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(jsonContent, Map.class);

            for (var x : (List) map.get("objects")) {
                FilmDto filmDto = new ModelMapper().map(x, FilmDto.class);
                filmDtos.add(filmDto);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return filmDtos;
    }

    @WithMockUser(username = "admin@admin.ru")
    @Test
    void testGetTopRatedMovies() {
        try {
            String content = new String(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("Top10RatedMovies.json")
                            .readAllBytes(),
                    StandardCharsets.UTF_8);

            FilmDtos filmDtos = jsonToFilmDtos(content);

            MvcResult result = mockMvc.perform(get("/api/film_rating/getTopRatedMovies")
                    .param("count", String.valueOf(10)))
                    .andExpect(status().is(200))
                    .andReturn();

            String jsonResponse = "{\"objects\":"
                    + result.getResponse().getContentAsString()
                    + "}";

            FilmDtos responseFilmDtos = jsonToFilmDtos(jsonResponse);
            Assertions.assertEquals(responseFilmDtos, filmDtos);
        } catch (Exception e) {

        }
    }

    @WithMockUser(username = "admin@admin.ru")
    @Test
    void testSetRating() {
        try {

            Principal mockPrincipal = Mockito.mock(Principal.class);
            Mockito.when(mockPrincipal.getName()).thenReturn("admin@admin.ru");

            RequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get("/api/film_rating/setRating")
                    .principal(mockPrincipal)
                    .param("filmId", String.valueOf(10))
                    .param("rating", String.valueOf(10))
                    .accept(MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();

            MockHttpServletResponse response = result.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class FilmDtos {
        public List<FilmDto> films = new ArrayList<>();

        void add(FilmDto filmDto) {
            this.films.add(filmDto);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FilmDtos)) return false;
            FilmDtos filmDtos = (FilmDtos) o;
            return films.equals(filmDtos.films);
        }

        @Override
        public int hashCode() {
            return Objects.hash(films);
        }
    }
}
