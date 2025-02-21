package com.alexkononon.star_wars_project.entity.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MissionFactionResourceId implements Serializable {

    @Column(name = "mission_id")
    private Long missionId;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "faction_id")
    private Long factionId;

    public MissionFactionResourceId() {
    }

    public MissionFactionResourceId(Long missionId, Long resourceId, Long factionId) {
        this.missionId = missionId;
        this.resourceId = resourceId;
        this.factionId = factionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MissionFactionResourceId)) return false;
        MissionFactionResourceId that = (MissionFactionResourceId) o;
        return Objects.equals(missionId, that.missionId) &&
                Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(factionId, that.factionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionId, resourceId, factionId);
    }

}
