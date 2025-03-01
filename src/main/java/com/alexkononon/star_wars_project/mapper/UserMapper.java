package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.UserDTO;
import com.alexkononon.star_wars_project.entity.security.Role;
import com.alexkononon.star_wars_project.entity.security.User;
import com.alexkononon.star_wars_project.repository.security.RoleRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", expression = "java(map(userDTO.getRole(), roleRepository))")
    User fromDtoToUser(UserDTO userDTO, @Context RoleRepository roleRepository);

    @Mapping(target = "role", source = "role.name")
    UserDTO fromUserToDTO(User user);

    default Role map(String roleName, @Context RoleRepository roleRepository) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
