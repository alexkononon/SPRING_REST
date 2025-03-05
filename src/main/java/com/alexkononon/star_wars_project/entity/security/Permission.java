package com.alexkononon.star_wars_project.entity.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Where(clause = "is_deleted = false")
@Table(name = "Permissions")
public class Permission {

    @EmbeddedId
    private PermissionId id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "can_read", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean canRead;

    @Column(name = "can_write", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean canWrite;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

}