package com.alexkononon.star_wars_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Planets")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private String description;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;


    @ManyToOne(optional = true)
    @JoinColumn(name = "faction_id", nullable = true)
    private Faction faction;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL)
    private Set<Location> locations = new HashSet<>();

}
