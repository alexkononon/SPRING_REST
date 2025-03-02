package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.LocationDTO;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.mapper.LocationMapper;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public LocationDTO createLocation(LocationDTO dto) {
        Location location = locationMapper.fromDtoToLocation(dto);
        location = locationRepository.save(location);
        return locationMapper.fromLocationToDTO(location);
    }

    public LocationDTO getLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return locationMapper.fromLocationToDTO(location);
    }

    public LocationDTO updateLocation(Long id, LocationDTO dto) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        locationMapper.updateLocationFromDto(dto, location);
        location = locationRepository.save(location);
        return locationMapper.fromLocationToDTO(location);
    }

    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Faction not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }
}