package chalange.backend.service;

import chalange.backend.dto.TagDto;
import chalange.backend.dto.UserDto;
import chalange.backend.entity.User;
import chalange.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("userService")
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDto> findAll(){
        return userRepository.findAllWithInheritedEntities().stream()
                .map(i -> modelMapper.map(i, UserDto.class))
                .collect(Collectors.toList());
    }

    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User doesn't not exists")
        );

        return User.toSecurityUser(user);
    }
}
