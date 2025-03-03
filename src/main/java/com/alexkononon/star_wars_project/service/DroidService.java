package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.DroidDTO;

/**
 * Service interface for managing droids.
 * Provides methods for creating, retrieving, updating, and deleting droids.
 */
public interface DroidService {

    /**
     * Creates a new droid based on the provided DTO.
     *
     * @param dto the droid data transfer object
     * @return the created droid as a DTO
     */
    DroidDTO createDroid(DroidDTO dto);

    /**
     * Retrieves the droid with the specified ID.
     *
     * @param id the unique identifier of the droid
     * @return the droid as a DTO
     * @throws RuntimeException if the droid is not found
     */
    DroidDTO getDroid(Long id);

    /**
     * Updates an existing droid with the provided data.
     *
     * @param id the unique identifier of the droid to update
     * @param dto the updated droid data transfer object
     * @return the updated droid as a DTO
     * @throws RuntimeException if the droid is not found
     */
    DroidDTO updateDroid(Long id, DroidDTO dto);

    /**
     * Deletes the droid with the specified ID.
     *
     * @param id the unique identifier of the droid to delete
     * @throws RuntimeException if the droid is not found
     */
    void deleteDroid(Long id);
}
