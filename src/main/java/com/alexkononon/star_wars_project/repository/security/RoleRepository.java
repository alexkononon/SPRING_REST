package com.alexkononon.star_wars_project.repository.security;

import com.alexkononon.star_wars_project.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

    @Query(value = "SELECT * FROM roles", nativeQuery = true)
    List<Role> findAllRolesIncludingDeleted();
}
