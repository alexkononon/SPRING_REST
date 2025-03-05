package com.alexkononon.star_wars_project.repository.security;

import com.alexkononon.star_wars_project.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllUsersIncludingDeleted();
}
