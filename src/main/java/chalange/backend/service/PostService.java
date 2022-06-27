package chalange.backend.service;

import chalange.backend.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
}
