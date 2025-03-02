package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class CharacterDTO {
    private Long id;
    private String name;
    private String status;
    private int currentXp;
    private String rank;
    private Integer maxSimultaneousMissions;
    private Integer completedMissions;
    private Set<Long> missionsId = new HashSet<>();
    private Set<Long> factionsId = new HashSet<>();
    private Long supremeId;
    private Set<Long> subordinatesId = new HashSet<>();
    private Long baseLocationId;
    private Long currentLocationId;
}

