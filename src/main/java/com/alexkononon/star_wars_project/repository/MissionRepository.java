package com.alexkononon.star_wars_project.repository;

import com.alexkononon.star_wars_project.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
}

