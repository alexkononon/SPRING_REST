package com.alexkononon.star_wars_project.repository;

import com.alexkononon.star_wars_project.entity.MissionStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionStatusHistoryRepository extends JpaRepository<MissionStatusHistory, Long> {
}

