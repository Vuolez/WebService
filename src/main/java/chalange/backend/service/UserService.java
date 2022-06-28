package chalange.backend.service;

import chalange.backend.dto.UserDto;
import chalange.backend.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    void save(UserDto userDto);
}
