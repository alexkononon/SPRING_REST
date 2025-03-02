package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class FactionDTO {
    private Long id;
    private String name;
    private String description;
    private Set<Long> enemyFactionIds = new HashSet<>();
    private Set<Long> planetIds = new HashSet<>();
    private Set<Long> memberIds = new HashSet<>();
}