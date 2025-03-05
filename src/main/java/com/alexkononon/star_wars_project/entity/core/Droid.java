package com.alexkononon.star_wars_project.entity.core;

import com.alexkononon.star_wars_project.enums.DroidSeries;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE droids SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "Droids")
public class Droid {
    @Id
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private DroidSeries series;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(length = 30)
    private String name;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private EntityObject entityObject;

}
