package com.alexkononon.star_wars_project.entity.core;

import com.alexkononon.star_wars_project.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE missions_status_history SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
@Table(name = "Missions_Status_History")
public class MissionStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status previous_status;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status new_status;

    @Column(nullable = false)
    private LocalDateTime changed_at;

    @Column(nullable = false)
    private long changedBy;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;


    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

}
