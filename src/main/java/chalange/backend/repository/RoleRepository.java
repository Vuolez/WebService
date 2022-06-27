package chalange.backend.repository;

import chalange.backend.entity.Role;
import chalange.backend.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleType(RoleType roleType);
}
