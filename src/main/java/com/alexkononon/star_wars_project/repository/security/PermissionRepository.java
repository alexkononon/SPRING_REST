package com.alexkononon.star_wars_project.repository.security;

import com.alexkononon.star_wars_project.entity.security.Permission;
import com.alexkononon.star_wars_project.entity.security.PermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, PermissionId> {
}

