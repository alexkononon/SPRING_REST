package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.MissionFactionResource;
import com.alexkononon.star_wars_project.entity.core.MissionFactionResourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionFactionResourceRepository extends JpaRepository<MissionFactionResource, MissionFactionResourceId> {
    @Query(value = "SELECT * FROM missions_resources", nativeQuery = true)
    List<MissionFactionResource> findAllMissionFactionResourcesIncludingDeleted();
}

