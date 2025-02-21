package com.alexkononon.star_wars_project.entity;

import com.alexkononon.star_wars_project.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Missions")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private int difficulty;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;


    @ManyToMany(mappedBy = "missions")
    private Set<Character> participants = new HashSet<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private Set<MissionStatusHistory> mission_status_histories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "mission_locations",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations = new HashSet<>();

}
