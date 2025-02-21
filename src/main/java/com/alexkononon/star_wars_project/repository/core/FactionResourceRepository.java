package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.FactionResource;
import com.alexkononon.star_wars_project.entity.core.FactionResourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionResourceRepository extends JpaRepository<FactionResource, FactionResourceId> {
}

