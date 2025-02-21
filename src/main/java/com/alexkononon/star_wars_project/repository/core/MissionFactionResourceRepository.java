package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.MissionFactionResource;
import com.alexkononon.star_wars_project.entity.core.MissionFactionResourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionFactionResourceRepository extends JpaRepository<MissionFactionResource, MissionFactionResourceId> {
}

