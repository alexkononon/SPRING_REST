package com.alexkononon.star_wars_project.entity.core;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FactionResourceId {
    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "faction_id")
    private Long factionId;

    public FactionResourceId() {
    }

    public FactionResourceId(Long resourceId, Long factionId) {
        this.resourceId = resourceId;
        this.factionId = factionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FactionResourceId)) return false;
        FactionResourceId that = (FactionResourceId) o;
        return Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(factionId, that.factionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId, factionId);
    }

}
