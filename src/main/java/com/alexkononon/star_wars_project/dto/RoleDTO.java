package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
    private Long id;
    private String name;
    private boolean isDeleted;
}
