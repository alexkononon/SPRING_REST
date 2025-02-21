package com.alexkononon.star_wars_project.repository;

import com.alexkononon.star_wars_project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
