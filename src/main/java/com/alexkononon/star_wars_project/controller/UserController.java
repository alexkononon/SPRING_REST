package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.dto.RegistrationRequest;
import com.alexkononon.star_wars_project.dto.UserDTO;
import com.alexkononon.star_wars_project.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<HttpStatus> register(@RequestBody RegistrationRequest registrationRequest) {
        UserDTO userRegistrationDTO = registrationRequest.getUserDTO();
        CharacterDTO characterDTO = registrationRequest.getCharacterDTO();
        userServiceImpl.register(userRegistrationDTO, characterDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody UserDTO user) {
        return userServiceImpl.verify(user);
    }

    @PatchMapping("/users/{id}/grant-role")
    public ResponseEntity<Void> updateUserRole(@PathVariable Long id, @RequestBody String request) {
        userServiceImpl.updateUserRole(id, request);
        return ResponseEntity.ok().build();
    }
}
