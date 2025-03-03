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
}
