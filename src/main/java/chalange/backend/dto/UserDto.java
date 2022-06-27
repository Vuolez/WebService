package chalange.backend.dto;

import chalange.backend.model.Role;
import chalange.backend.model.Status;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Status status;
}
