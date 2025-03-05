package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.mapper.PlanetMapper;
import com.alexkononon.star_wars_project.repository.core.FactionRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import com.alexkononon.star_wars_project.service.PlanetService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanetServiceImpl implements PlanetService {

    private final PlanetMapper planetMapper;
    private final PlanetRepository planetRepository;
    private final FactionRepository factionRepository;
    private final LocationRepository locationRepository;
    private final LocationServiceImpl locationService;

    public PlanetServiceImpl(PlanetMapper planetMapper, PlanetRepository planetRepository,
                             FactionRepository factionRepository, LocationRepository locationRepository, LocationServiceImpl locationService) {
        this.planetMapper = planetMapper;
        this.planetRepository = planetRepository;
        this.factionRepository = factionRepository;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }

    public PlanetDTO createPlanet(PlanetDTO planetDTO) {
        Planet planet = planetMapper.fromDtoToPlanet(planetDTO, factionRepository, locationRepository);
        planet = planetRepository.save(planet);
        return planetMapper.fromPlanetToDTO(planet);
    }

    public PlanetDTO getPlanet(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id));
        return planetMapper.fromPlanetToDTO(planet);
    }

    public List<PlanetDTO> getAllPlanets() {
        return planetRepository.findAll().stream()
                .map(planetMapper::fromPlanetToDTO)
                .collect(Collectors.toList());
    }

    public PlanetDTO updatePlanet(Long id, PlanetDTO planetDTO) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id));

        planetMapper.updatePlanetFromDto(planetDTO, planet, factionRepository, locationRepository);

        planet = planetRepository.save(planet);
        return planetMapper.fromPlanetToDTO(planet);
    }

    public void deletePlanet(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id));

        for (Location location : planet.getLocations()) {
            try {
                locationService.deleteLocation(location.getId());
            } catch (RuntimeException ex) {
                throw new RuntimeException("Cannot delete planet because location with id " + location.getId()
                        + " cannot be deleted: " + ex.getMessage());
            }
        }

        planetRepository.delete(planet);
    }

}
