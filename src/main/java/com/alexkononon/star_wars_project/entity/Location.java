package com.alexkononon.star_wars_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private String description;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;


    @ManyToOne(optional = true)
    @JoinColumn(name = "planet_id", nullable = true)
    private Planet planet;

    @ManyToMany(mappedBy = "locations")
    private Set<Mission> missions = new HashSet<>();

    @OneToMany(mappedBy = "baseLocation", cascade = CascadeType.ALL)
    private Set<Character> livingCharacters = new HashSet<>();

    @OneToMany(mappedBy = "currentLocation", cascade = CascadeType.ALL)
    private Set<Character> stayingCharacters = new HashSet<>();

}

