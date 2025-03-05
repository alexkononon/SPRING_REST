package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.MissionStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionStatusHistoryRepository extends JpaRepository<MissionStatusHistory, Long> {
    @Query(value = "SELECT * FROM missions_status_history", nativeQuery = true)
    List<MissionStatusHistory> findAllMissionStatusHistoriesIncludingDeleted();
}

