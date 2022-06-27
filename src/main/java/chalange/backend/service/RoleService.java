package chalange.backend.service;

import chalange.backend.dto.RoleDto;
import chalange.backend.model.RoleType;

import java.util.Optional;

public interface RoleService {
    RoleDto findRoleByRoleType(RoleType roleType);
}
