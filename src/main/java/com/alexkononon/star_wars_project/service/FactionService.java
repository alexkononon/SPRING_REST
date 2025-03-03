package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.FactionDTO;

/**
 * Service interface for managing factions.
 * Provides methods for creating, retrieving, updating, and deleting factions.
 */
public interface FactionService {

    /**
     * Creates a new faction based on the provided DTO.
     *
     * @param dto the faction data transfer object
     * @return the created faction as a DTO
     */
    FactionDTO createFaction(FactionDTO dto);

    /**
     * Retrieves the faction with the specified ID.
     *
     * @param id the unique identifier of the faction
     * @return the faction as a DTO
     * @throws RuntimeException if the faction is not found
     */
    FactionDTO getFaction(Long id);

    /**
     * Updates an existing faction with the provided data.
     *
     * @param id the unique identifier of the faction to update
     * @param dto the updated faction data transfer object
     * @return the updated faction as a DTO
     * @throws RuntimeException if the faction is not found
     */
    FactionDTO updateFaction(Long id, FactionDTO dto);

    /**
     * Deletes the faction with the specified ID.
     *
     * @param id the unique identifier of the faction to delete
     * @throws RuntimeException if the faction is not found
     */
    void deleteFaction(Long id);
}
