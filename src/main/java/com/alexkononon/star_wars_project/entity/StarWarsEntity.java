package com.alexkononon.star_wars_project.entity;

import com.alexkononon.star_wars_project.enums.EntityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Entities")
public class StarWarsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;

}
