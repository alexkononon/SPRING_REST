package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.EntityObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<EntityObject, Long> {
    @Query(value = "SELECT * FROM entities", nativeQuery = true)
    List<EntityObject> findAllEntitiesIncludingDeleted();
}

