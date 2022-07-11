package chalange.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
    USER("user:read"),
    ADMIN("user:write");

    private String type;
}
