package com.alexkononon.star_wars_project.repository;

import com.alexkononon.star_wars_project.entity.Permission;
import com.alexkononon.star_wars_project.entity.PermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, PermissionId> {
}

