package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.CharacterDTO;

/**
 * Service interface for managing characters.
 * Provides methods for creating, retrieving, updating, and deleting characters.
 */
public interface CharacterService {

    /**
     * Creates a new character based on the provided DTO.
     *
     * @param characterDTO the character data transfer object
     * @return the created character as a DTO
     */
    CharacterDTO createCharacter(CharacterDTO characterDTO);

    /**
     * Retrieves the character with the specified ID.
     *
     * @param id the unique identifier of the character
     * @return the character as a DTO
     * @throws RuntimeException if the character is not found
     */
    CharacterDTO getCharacter(Long id);

    /**
     * Updates an existing character with the provided data.
     *
     * @param id the unique identifier of the character to update
     * @param characterDTO the updated character data transfer object
     * @return the updated character as a DTO
     * @throws RuntimeException if the character is not found
     */
    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    /**
     * Deletes the character with the specified ID.
     *
     * @param id the unique identifier of the character to delete
     * @throws RuntimeException if the character is not found
     */
    void deleteCharacter(Long id);

    /**
     * Changes the current location of a character.
     * <p>
     * This method updates the "current location" for the character identified by {@code characterId}
     * to the location specified by {@code newLocationId} without any additional business rule checks.
     * It throws a {@code RuntimeException} if either the character or the new location is not found.
     *
     * @param characterId  the unique identifier of the character whose current location is to be updated
     * @param newLocationId the unique identifier of the new location to be set as current
     * @throws RuntimeException if the character or the location is not found
     */
    void changeCurrentLocation(Long characterId, Long newLocationId);

    /**
     * Changes the home location (base location) of a character with conflict validation.
     * <p>
     * This method updates the "home location" (or base location) for the character identified by {@code characterId}
     * to the location specified by {@code newLocationId}. If the new location is associated with a planet,
     * and that planet belongs to a faction, the method verifies that none of the character's factions consider
     * that faction as an enemy. If a conflict is detected, a {@code RuntimeException} is thrown.
     *
     * @param characterId  the unique identifier of the character whose home location is to be updated
     * @param newLocationId the unique identifier of the new location to be set as the home location
     * @throws RuntimeException if the character or the location is not found, or if the planet's faction is hostile
     */
    void changeHomeLocation(Long characterId, Long newLocationId);

}
