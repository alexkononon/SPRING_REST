package com.alexkononon.star_wars_project.entity.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE factions SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "Factions")
public class Faction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private String description;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "faction_conflicts",
            joinColumns = @JoinColumn(name = "faction1_id"),
            inverseJoinColumns = @JoinColumn(name = "faction2_id")
    )
    private Set<Faction> enemyFactions = new HashSet<>();

    @ManyToMany(mappedBy = "enemyFactions")
    private Set<Faction> enemyOf = new HashSet<>();

    @OneToMany(mappedBy = "faction", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Planet> planets = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "faction_characters",
            joinColumns = @JoinColumn(name = "faction_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private Set<Character> members = new HashSet<>();


}

