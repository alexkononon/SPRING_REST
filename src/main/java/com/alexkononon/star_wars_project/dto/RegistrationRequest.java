package com.alexkononon.star_wars_project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {
    @JsonProperty("user")
    private UserDTO userDTO;
    @JsonProperty("character")
    private CharacterDTO characterDTO;
}
