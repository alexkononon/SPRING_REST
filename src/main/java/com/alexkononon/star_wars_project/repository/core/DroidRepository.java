package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.Droid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroidRepository extends JpaRepository<Droid, Long> {
    @Query(value = "SELECT * FROM droids", nativeQuery = true)
    List<Droid> findAllDroidsIncludingDeleted();
}

