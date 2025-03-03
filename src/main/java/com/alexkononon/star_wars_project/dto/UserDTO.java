package com.alexkononon.star_wars_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    @JsonIgnore
    private Long id;
    private String username;
    private String password;
    @JsonIgnore
    private String role;
}
