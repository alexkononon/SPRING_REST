package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.LocationDTO;

/**
 * Service interface for managing locations.
 * Provides methods for creating, retrieving, updating, and deleting locations.
 */
public interface LocationService {

    /**
     * Creates a new location based on the provided DTO.
     *
     * @param dto the location data transfer object
     * @return the created location as a DTO
     */
    LocationDTO createLocation(LocationDTO dto);

    /**
     * Retrieves the location with the specified ID.
     *
     * @param id the unique identifier of the location
     * @return the location as a DTO
     * @throws RuntimeException if the location is not found
     */
    LocationDTO getLocation(Long id);

    /**
     * Updates an existing location with the provided data.
     *
     * @param id the unique identifier of the location to update
     * @param dto the updated location data transfer object
     * @return the updated location as a DTO
     * @throws RuntimeException if the location is not found
     */
    LocationDTO updateLocation(Long id, LocationDTO dto);

    /**
     * Deletes the location with the specified ID.
     *
     * @param id the unique identifier of the location to delete
     * @throws RuntimeException if the location is not found
     */
    void deleteLocation(Long id);
}
