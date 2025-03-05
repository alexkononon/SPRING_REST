package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.FactionDTO;

import java.util.List;

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

    /**
     * Initiates a conflict between two factions.
     * <p>
     * This method sets both factions (identified by {@code factionId} and {@code targetFactionId})
     * to a conflict state. It may include business logic to ensure that neither faction is already
     * in conflict and to validate the conditions for starting a new conflict.
     * </p>
     *
     * @param factionId the unique identifier of the faction that initiates the conflict
     * @param targetFactionId the unique identifier of the faction to be in conflict with
     * @return a {@link FactionDTO} representing the initiating faction with its updated conflict status
     * @throws RuntimeException if either faction is not found or if one of the factions is already in conflict
     */
    FactionDTO startConflictBetween(Long factionId, Long targetFactionId);

    /**
     * Retrieves all factions that are currently in conflict with the given faction.
     * <p>
     * This method returns a list of factions that have a conflict relationship with the faction
     * identified by {@code factionId}. It consolidates all enemy factions from both sides of the relationship.
     * </p>
     *
     * @param factionId the unique identifier of the faction for which to retrieve conflicting factions
     * @return a list of {@link FactionDTO} representing all factions in conflict with the specified faction
     * @throws RuntimeException if the faction with the given id is not found
     */
    List<FactionDTO> getConflictingFactions(Long factionId);

}
