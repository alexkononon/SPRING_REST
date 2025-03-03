package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.ResourceDTO;
import java.util.List;

/**
 * Service interface for managing resources.
 * Provides methods for creating, retrieving, updating, deleting, and listing resources.
 */
public interface ResourceService {

    /**
     * Creates a new resource based on the provided DTO.
     *
     * @param resourceDTO the resource data transfer object
     * @return the created resource as a DTO
     */
    ResourceDTO createResource(ResourceDTO resourceDTO);

    /**
     * Retrieves the resource with the specified ID.
     *
     * @param id the unique identifier of the resource
     * @return the resource as a DTO
     * @throws RuntimeException if the resource is not found
     */
    ResourceDTO getResource(Long id);

    /**
     * Retrieves all resources.
     *
     * @return a list of all resources as DTOs
     */
    List<ResourceDTO> getAllResources();

    /**
     * Updates an existing resource with the provided data.
     *
     * @param id the unique identifier of the resource to update
     * @param resourceDTO the updated resource data transfer object
     * @return the updated resource as a DTO
     * @throws RuntimeException if the resource is not found
     */
    ResourceDTO updateResource(Long id, ResourceDTO resourceDTO);

    /**
     * Deletes the resource with the specified ID.
     *
     * @param id the unique identifier of the resource to delete
     * @throws RuntimeException if the resource is not found
     */
    void deleteResource(Long id);
}
