package com.alexkononon.star_wars_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;


    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isDeleted;


    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id")
    private Character character;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;


}
