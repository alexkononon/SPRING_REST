package com.alexkononon.star_wars_project.entity.core;

import com.alexkononon.star_wars_project.enums.EntityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE entities SET is_deleted = true WHERE id = ?")
@Table(name = "Entities")
public class EntityObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;
}
