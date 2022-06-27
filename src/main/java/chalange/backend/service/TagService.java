package chalange.backend.service;

import chalange.backend.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> findAll();
}
