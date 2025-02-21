package com.alexkononon.star_wars_project.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PermissionId {

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "entity", length = 50)
    private String entity;

    public PermissionId() {}

    public PermissionId(Long roleId, String entity) {
        this.roleId = roleId;
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionId)) return false;
        PermissionId that = (PermissionId) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, entity);
    }

}

