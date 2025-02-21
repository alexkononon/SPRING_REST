package com.alexkononon.star_wars_project.entity.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Resources_Factions")
public class FactionResource {
    @EmbeddedId
    private FactionResourceId id;


    @MapsId("resourceId")
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;


    @MapsId("factionId")
    @ManyToOne
    @JoinColumn(name = "faction_id", nullable = false)
    private Faction faction;

    private Long amount;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

}

