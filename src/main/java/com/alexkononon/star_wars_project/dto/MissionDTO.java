package com.alexkononon.star_wars_project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MissionDTO {
    private Long id;
    private String title;
    private String status;
    private int difficulty;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private boolean isDeleted;
    private Set<Long> participantsId = new HashSet<>();
    private Set<Long> missionStatusHistoriesId = new HashSet<>();
    private Set<Long> locationsId = new HashSet<>();
}
