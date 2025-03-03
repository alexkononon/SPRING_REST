package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.PlanetDTO;

/**
 * Service interface for managing planets.
 * Provides methods for creating, retrieving, updating, and deleting planets.
 */
public interface PlanetService {

    /**
     * Creates a new planet based on the provided DTO.
     *
     * @param planetDTO the planet data transfer object
     * @return the created planet as a DTO
     */
    PlanetDTO createPlanet(PlanetDTO planetDTO);

    /**
     * Retrieves the planet with the specified ID.
     *
     * @param id the unique identifier of the planet
     * @return the planet as a DTO
     * @throws RuntimeException if the planet is not found
     */
    PlanetDTO getPlanet(Long id);

    /**
     * Updates an existing planet with the provided data.
     *
     * @param id the unique identifier of the planet to update
     * @param planetDTO the updated planet data transfer object
     * @return the updated planet as a DTO
     * @throws RuntimeException if the planet is not found
     */
    PlanetDTO updatePlanet(Long id, PlanetDTO planetDTO);

    /**
     * Deletes the planet with the specified ID.
     *
     * @param id the unique identifier of the planet to delete
     * @throws RuntimeException if the planet is not found
     */
    void deletePlanet(Long id);
}
