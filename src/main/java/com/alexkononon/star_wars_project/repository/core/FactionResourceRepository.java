package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.FactionResource;
import com.alexkononon.star_wars_project.entity.core.FactionResourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactionResourceRepository extends JpaRepository<FactionResource, FactionResourceId> {
    @Query(value = "SELECT * FROM resources_factions", nativeQuery = true)
    List<FactionResource> findAllFactionResourcesIncludingDeleted();
}

