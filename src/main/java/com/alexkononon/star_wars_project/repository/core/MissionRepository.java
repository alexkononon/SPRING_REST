package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query(value = "SELECT * FROM missions", nativeQuery = true)
    List<Mission> findAllMissionsIncludingDeleted();
}

