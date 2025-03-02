package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.LocationDTO;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.mapper.LocationMapper;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import com.alexkononon.star_wars_project.repository.core.MissionRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final PlanetRepository planetRepository;
    private final MissionRepository missionRepository;
    private final CharacterRepository characterRepository;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper,
                           PlanetRepository planetRepository, MissionRepository missionRepository,
                           CharacterRepository characterRepository) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.planetRepository = planetRepository;
        this.missionRepository = missionRepository;
        this.characterRepository = characterRepository;
    }

    public LocationDTO createLocation(LocationDTO dto) {
        Location location = locationMapper.fromDtoToLocation(dto, planetRepository, missionRepository, characterRepository);
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
        locationMapper.updateLocationFromDto(dto, location, planetRepository, missionRepository, characterRepository);
        location = locationRepository.save(location);
        return locationMapper.fromLocationToDTO(location);
    }

    public void deleteLocation(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new RuntimeException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }
}
