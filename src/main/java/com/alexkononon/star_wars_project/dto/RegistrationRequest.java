package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    private UserDTO userDTO;
    private CharacterDTO characterDTO;
}
