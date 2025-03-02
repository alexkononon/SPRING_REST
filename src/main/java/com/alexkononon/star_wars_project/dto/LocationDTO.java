package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class LocationDTO {
    private Long id;
    private String name;
    private String description;
    private Long planetId;
    private Set<Long> missionsId = new HashSet<>();
    private Set<Long> livingCharactersId = new HashSet<>();
    private Set<Long> stayingCharactersId = new HashSet<>();
}