package chalange.backend.service;

import chalange.backend.dto.TagDto;
import chalange.backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("tagService")
@RequiredArgsConstructor
public class TagService { // todo: добавить интерфейсы ко всем сервисам
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public List<TagDto> findAll() {
        return tagRepository.findAll().stream()
                .map(i -> modelMapper.map(i, TagDto.class))
                .collect(Collectors.toList());
    }
}
