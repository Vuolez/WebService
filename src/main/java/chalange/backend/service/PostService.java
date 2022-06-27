package chalange.backend.service;

import chalange.backend.dto.PostDto;
import chalange.backend.entity.Post;
import chalange.backend.entity.User;
import chalange.backend.repository.PostRepository;
import chalange.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("postService")
public class PostService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    public List<PostDto> findAll(){
        return postRepository.findAll().stream()
                .map(i -> modelMapper.map(i, PostDto.class))
                .collect(Collectors.toList());
    }
}
