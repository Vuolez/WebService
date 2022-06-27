package chalange.backend.dto;

import chalange.backend.model.RoleType;
import chalange.backend.model.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<RoleDto> roles;
}
