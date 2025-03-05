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
@SQLDelete(sql = "UPDATE locations SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
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


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "planet_id", nullable = true)
    private Planet planet;

    @ManyToMany(mappedBy = "locations", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Mission> missions = new HashSet<>();

    @OneToMany(mappedBy = "baseLocation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Character> livingCharacters = new HashSet<>();

    @OneToMany(mappedBy = "currentLocation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Character> stayingCharacters = new HashSet<>();

}

