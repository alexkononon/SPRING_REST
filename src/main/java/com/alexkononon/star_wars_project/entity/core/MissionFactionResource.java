package com.alexkononon.star_wars_project.entity.core;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE Missions_Resources SET is_deleted = true WHERE mission_id = ? AND resource_id = ? AND faction_id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "Missions_Resources")
public class MissionFactionResource {

    @EmbeddedId
    private MissionFactionResourceId id;

    @MapsId("missionId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @MapsId("resourceId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @MapsId("factionId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "faction_id", nullable = false)
    private Faction faction;

    private Long amount;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

}
