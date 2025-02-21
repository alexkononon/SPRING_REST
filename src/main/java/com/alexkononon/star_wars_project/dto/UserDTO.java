package com.alexkononon.star_wars_project.dto;

import com.alexkononon.star_wars_project.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends CharacterDTO {

    private String username;
    private String passwordHash;
    private Long roleId;

    public UserDTO(User user) {
        super(user.getCharacter());
        this.username = user.getUsername();
        this.passwordHash = user.getPasswordHash();
        this.roleId = user.getRole().getId();
    }
}