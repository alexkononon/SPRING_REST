package com.alexkononon.star_wars_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Roles")
public class Role {
        @Id
        private Long id;

        @Column(nullable = false, length = 50)
        private String name;

        @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
        private boolean isDeleted;


        @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
        private Set<User> users = new HashSet<>();

        @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<Permission> permissions = new HashSet<>();

}
