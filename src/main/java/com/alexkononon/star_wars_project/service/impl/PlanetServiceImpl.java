package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.mapper.PlanetMapper;
import com.alexkononon.star_wars_project.repository.core.FactionRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import com.alexkononon.star_wars_project.service.PlanetService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlanetServiceImpl implements PlanetService {

    private final PlanetMapper planetMapper;
    private final PlanetRepository planetRepository;
    private final FactionRepository factionRepository;
    private final LocationRepository locationRepository;

    public PlanetServiceImpl(PlanetMapper planetMapper, PlanetRepository planetRepository,
                             FactionRepository factionRepository, LocationRepository locationRepository) {
        this.planetMapper = planetMapper;
        this.planetRepository = planetRepository;
        this.factionRepository = factionRepository;
        this.locationRepository = locationRepository;
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

    public PlanetDTO updatePlanet(Long id, PlanetDTO planetDTO) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id));

        planetMapper.updatePlanetFromDto(planetDTO, planet, factionRepository, locationRepository);

        planet = planetRepository.save(planet);
        return planetMapper.fromPlanetToDTO(planet);
    }

    public void deletePlanet(Long id) {
        if (!planetRepository.existsById(id)) {
            throw new RuntimeException("Planet not found with id: " + id);
        }
        planetRepository.deleteById(id);
    }
}
