package com.alexkononon.star_wars_project.dto;

import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.Faction;
import com.alexkononon.star_wars_project.entity.core.Mission;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;


import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@JsonIgnoreProperties({"entityType", "deleted"})
@Getter
@Setter
@NoArgsConstructor
public class CharacterDTO {
    //Required fields
    private Long id;
    private String name;
    private String status;
    private int currentXp;
    private boolean isDeleted;
    //Required dependencies
    private Long currentLocationId;

    //Optional fields
    private String rank;
    private Integer maxSimultaneousMissions;
    private Integer completedMissions;
    //Optional dependencies
    private Set<Long> missionIds;
    private Set<Long> factionIds;
    private Long supremeId;
    private Set<Long> subordinateIds;
    private Long baseLocationId;

    public CharacterDTO(Character character) {
        this.id = character.getId();
        this.name = character.getName();
        this.status = character.getStatus();
        this.currentXp = character.getCurrent_xp();
        this.rank = character.getRank();
        this.maxSimultaneousMissions = character.getMax_simultaneous_missions();
        this.completedMissions = character.getCompleted_missions();
        this.currentLocationId = character.getCurrentLocation().getId();
        this.isDeleted = character.isDeleted();

        if (character.getMissions() != null) {
            this.missionIds = character.getMissions().stream()
                    .map(Mission::getId)
                    .collect(Collectors.toSet());
        }

        if (character.getFactions() != null) {
            this.factionIds = character.getFactions().stream()
                    .map(Faction::getId)
                    .collect(Collectors.toSet());
        }

        if (character.getSupreme() != null) {
            this.supremeId = character.getSupreme().getId();
        }

        if (character.getSubordinates() != null) {
            this.subordinateIds = character.getSubordinates().stream()
                    .map(Character::getId)
                    .collect(Collectors.toSet());
        }

        if (character.getBaseLocation() != null) {
            this.baseLocationId = character.getBaseLocation().getId();
        }
    }
}

