package chalange.backend.service;

import chalange.backend.dto.RoleDto;
import chalange.backend.dto.UserDto;
import chalange.backend.entity.Role;
import chalange.backend.model.RoleType;
import chalange.backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service("roleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleDto findRoleByRoleType(RoleType roleType) {

        return modelMapper.map(roleRepository.findRoleByRoleType(roleType).get(), RoleDto.class);
    }
}
