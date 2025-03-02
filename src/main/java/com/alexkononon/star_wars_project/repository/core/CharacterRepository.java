package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("SELECT c FROM Character c " +
            "LEFT JOIN FETCH c.missions " +
            "LEFT JOIN FETCH c.factions " +
            "LEFT JOIN FETCH c.subordinates " +
            "LEFT JOIN FETCH c.supreme " +
            "LEFT JOIN FETCH c.baseLocation " +
            "LEFT JOIN FETCH c.currentLocation " +
            "WHERE c.id = :id")
    Optional<Character> findByIdWithRelations(@Param("id") Long id);
}

