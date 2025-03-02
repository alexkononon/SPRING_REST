package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlanetDTO {
    private Long id;
    private String name;
    private String description;
    private Long factionId;
    private Set<Long> locations = new HashSet<>();
}
