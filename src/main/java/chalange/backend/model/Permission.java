package chalange.backend.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Permission {
    PLAYER_READ("user:read"),
    PLAYER_WRITE("user:write");

    private final String permission;

    public String getPermission(){
        return permission;
    }
}
