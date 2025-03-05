package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.LocationDTO;
import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.enums.Status;
import com.alexkononon.star_wars_project.mapper.LocationMapper;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import com.alexkononon.star_wars_project.repository.core.MissionRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import com.alexkononon.star_wars_project.service.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final PlanetRepository planetRepository;
    private final MissionRepository missionRepository;
    private final CharacterRepository characterRepository;

    public LocationServiceImpl(LocationRepository locationRepository, LocationMapper locationMapper,
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
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        return locationMapper.fromLocationToDTO(location);
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(locationMapper::fromLocationToDTO)
                .collect(Collectors.toList());
    }

    public LocationDTO updateLocation(Long id, LocationDTO dto) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        locationMapper.updateLocationFromDto(dto, location, planetRepository, missionRepository, characterRepository);
        location = locationRepository.save(location);
        return locationMapper.fromLocationToDTO(location);
    }

    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));

        boolean hasActiveMissions = location.getMissions().stream()
                .anyMatch(mission -> mission.getStatus() == Status.IN_PROGRESS);

        if (hasActiveMissions) {
            throw new RuntimeException("Cannot delete location: there are active missions associated with the location.");
        }

        locationRepository.deleteById(id);
    }
}
