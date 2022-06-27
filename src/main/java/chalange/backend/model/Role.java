package chalange.backend.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public enum Role {
    USER(Set.of(Permission.PLAYER_READ)),
    ADMIN(Set.of(Permission.PLAYER_READ, Permission.PLAYER_WRITE));

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
