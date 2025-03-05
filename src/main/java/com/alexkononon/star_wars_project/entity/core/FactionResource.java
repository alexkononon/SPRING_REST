package com.alexkononon.star_wars_project.entity.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE Resources_Factions SET is_deleted = true WHERE resource_id = ? AND faction_id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "Resources_Factions")
public class FactionResource {
    @EmbeddedId
    private FactionResourceId id;


    @MapsId("resourceId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;


    @MapsId("factionId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "faction_id", nullable = false)
    private Faction faction;

    private Long amount;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

}

