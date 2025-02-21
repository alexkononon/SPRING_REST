package com.alexkononon.star_wars_project.dto;

import com.alexkononon.star_wars_project.enums.EntityType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarWarsEntityDTO {

    private Long id;
    private EntityType entityType;
    private boolean isDeleted;

    public StarWarsEntityDTO(Long id, EntityType entityType, boolean isDeleted) {
        this.id = id;
        this.entityType = entityType;
        this.isDeleted = isDeleted;
    }
}

