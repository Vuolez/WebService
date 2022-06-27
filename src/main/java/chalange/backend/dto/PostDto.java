package chalange.backend.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String content;
    private Long UserId;
}