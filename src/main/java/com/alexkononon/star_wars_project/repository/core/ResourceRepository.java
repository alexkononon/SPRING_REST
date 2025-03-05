package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query(value = "SELECT * FROM resources", nativeQuery = true)
    List<Resource> findAllResourcesIncludingDeleted();
}

