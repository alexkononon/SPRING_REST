package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.dto.UserDTO;
import com.alexkononon.star_wars_project.mapper.CharacterMapper;
import com.alexkononon.star_wars_project.mapper.UserMapper;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.security.RoleRepository;
import com.alexkononon.star_wars_project.repository.security.UserRepository;
import com.alexkononon.star_wars_project.security.service.JWTService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.alexkononon.star_wars_project.entity.security.User;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    CharacterMapper characterMapper;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void register(UserDTO userDTO, CharacterDTO characterDTO) {
        CharacterDTO character = characterService.createCharacter(characterDTO);

        User user = userMapper.fromDtoToUser(userDTO, roleRepository);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCharacter(characterRepository.findById(character.getId()).orElseThrow());
        userRepository.save(user);
        userMapper.fromUserToDTO(user);
    }

    public String verify(UserDTO userDTO) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDTO.getUsername())  ;
        } else {
            return "fail";
        }
    }
}
