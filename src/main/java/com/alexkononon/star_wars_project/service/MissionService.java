package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.MissionDTO;

/**
 * Service interface for managing missions.
 * Provides methods for creating, retrieving, updating, and deleting missions.
 */
public interface MissionService {

    /**
     * Creates a new mission based on the provided DTO.
     *
     * @param dto the mission data transfer object
     * @return the created mission as a DTO
     */
    MissionDTO createMission(MissionDTO dto);

    /**
     * Retrieves the mission with the specified ID.
     *
     * @param id the unique identifier of the mission
     * @return the mission as a DTO
     * @throws RuntimeException if the mission is not found
     */
    MissionDTO getMission(Long id);

    /**
     * Updates an existing mission with the provided data.
     *
     * @param id the unique identifier of the mission to update
     * @param dto the updated mission data transfer object
     * @return the updated mission as a DTO
     * @throws RuntimeException if the mission is not found
     */
    MissionDTO updateMission(Long id, MissionDTO dto);

    /**
     * Deletes the mission with the specified ID.
     *
     * @param id the unique identifier of the mission to delete
     * @throws RuntimeException if the mission is not found
     */
    void deleteMission(Long id);
}
