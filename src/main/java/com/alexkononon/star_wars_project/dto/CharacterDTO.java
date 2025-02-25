package com.alexkononon.star_wars_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;


import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties({"entityType", "deleted", "id"})
@Getter
@Setter
@NoArgsConstructor
public class CharacterDTO {
    private Long id;
    private String name;
    private String status;
    private int currentXp;
    private boolean isDeleted;
    private Long currentLocationId;

    private String rank;
    private Integer maxSimultaneousMissions;
    private Integer completedMissions;
}

