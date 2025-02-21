package com.alexkononon.star_wars_project.repository;

import com.alexkononon.star_wars_project.entity.MissionFactionResource;
import com.alexkononon.star_wars_project.entity.MissionFactionResourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionFactionResourceRepository extends JpaRepository<MissionFactionResource, MissionFactionResourceId> {
}

