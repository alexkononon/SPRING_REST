package com.alexkononon.star_wars_project.repository;

import com.alexkononon.star_wars_project.entity.FactionResource;
import com.alexkononon.star_wars_project.entity.FactionResourceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionResourceRepository extends JpaRepository<FactionResource, FactionResourceId> {
}

