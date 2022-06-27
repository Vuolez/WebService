package chalange.backend.service;

import chalange.backend.dto.PostDto;
import chalange.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("postService")
public class PostServiceImpl implements PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    @Override
    public List<PostDto> findAll() { // todo: опционально - сделать общий дженерик метод
        return postRepository.findAll().stream()
                .map(i -> modelMapper.map(i, PostDto.class))
                .collect(Collectors.toList());
    }
}
