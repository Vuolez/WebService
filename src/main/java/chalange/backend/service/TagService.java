package chalange.backend.service;

import chalange.backend.dto.TagDto;
import chalange.backend.entity.Post;
import chalange.backend.entity.Tag;
import chalange.backend.repository.PostRepository;
import chalange.backend.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("tagService")
public class TagService {
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public List<TagDto> findAll(){
        return tagRepository.findAll().stream()
                .map(i -> modelMapper.map(i, TagDto.class))
                .collect(Collectors.toList());
    }
}
