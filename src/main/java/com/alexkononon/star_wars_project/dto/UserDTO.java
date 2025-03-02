package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String role;
}
