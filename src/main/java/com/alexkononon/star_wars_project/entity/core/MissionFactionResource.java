package com.alexkononon.star_wars_project.entity.core;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE Missions_Resources SET is_deleted = true WHERE mission_id = ? AND resource_id = ? AND faction_id = ?")
@Table(name = "Missions_Resources")
public class MissionFactionResource {

    @EmbeddedId
    private MissionFactionResourceId id;

    @MapsId("missionId")
    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @MapsId("resourceId")
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @MapsId("factionId")
    @ManyToOne
    @JoinColumn(name = "faction_id", nullable = false)
    private Faction faction;

    private Long amount;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

}
