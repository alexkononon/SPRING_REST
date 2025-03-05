package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactionRepository extends JpaRepository<Faction, Long> {
    @Query(value = "SELECT * FROM factions", nativeQuery = true)
    List<Faction> findAllFactionsIncludingDeleted();
}

