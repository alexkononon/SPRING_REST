package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.dto.UserDTO;
import com.alexkononon.star_wars_project.entity.security.Role;
import com.alexkononon.star_wars_project.mapper.CharacterMapper;
import com.alexkononon.star_wars_project.mapper.UserMapper;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.security.RoleRepository;
import com.alexkononon.star_wars_project.repository.security.UserRepository;
import com.alexkononon.star_wars_project.security.service.JWTService;
import com.alexkononon.star_wars_project.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.alexkononon.star_wars_project.entity.security.User;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    final CharacterMapper characterMapper;

    private final CharacterServiceImpl characterServiceImpl;

    private final CharacterRepository characterRepository;

    private final JWTService jwtService;

    final AuthenticationManager authManager;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, CharacterMapper characterMapper, CharacterServiceImpl characterServiceImpl, CharacterRepository characterRepository, JWTService jwtService, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.characterMapper = characterMapper;
        this.characterServiceImpl = characterServiceImpl;
        this.characterRepository = characterRepository;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public void register(UserDTO userDTO, CharacterDTO characterDTO) {
        userDTO.setRole("USER");

        CharacterDTO character = characterServiceImpl.createCharacter(characterDTO);

        User user = userMapper.fromDtoToUser(userDTO, roleRepository);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCharacter(characterRepository.findById(character.getId()).orElseThrow());
        userRepository.save(user);
    }

    public String verify(UserDTO userDTO) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDTO.getUsername())  ;
        } else {
            return "Failed";
        }
    }

    public void updateUserRole(Long userId, String newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Role role = roleRepository.findByName(newRole)
                .orElseThrow(() -> new RuntimeException("Role not found: " + newRole));

        user.setRole(role);
        userRepository.save(user);
    }
}
