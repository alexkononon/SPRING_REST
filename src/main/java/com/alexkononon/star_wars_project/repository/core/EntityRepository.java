package com.alexkononon.star_wars_project.repository.core;

import com.alexkononon.star_wars_project.entity.core.EntityObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<EntityObject, Long> {
}

