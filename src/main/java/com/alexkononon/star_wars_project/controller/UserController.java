package com.alexkononon.star_wars_project.controller;


import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.dto.RegistrationRequest;
import com.alexkononon.star_wars_project.dto.UserDTO;
import com.alexkononon.star_wars_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apic")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody RegistrationRequest registrationRequest) {
        UserDTO userRegistrationDTO = registrationRequest.getUserDTO();
        CharacterDTO characterDTO = registrationRequest.getCharacterDTO();
        userService.register(userRegistrationDTO, characterDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
