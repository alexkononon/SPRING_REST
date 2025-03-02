package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.mapper.PlanetMapper;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlanetService {

    private final PlanetMapper planetMapper;
    private final PlanetRepository planetRepository;

    public PlanetService(PlanetMapper planetMapper, PlanetRepository planetRepository) {
        this.planetMapper = planetMapper;
        this.planetRepository = planetRepository;
    }

    public PlanetDTO createPlanet(PlanetDTO planetDTO) {
        Planet planet = planetMapper.fromDtoToPlanet(planetDTO);
        planet = planetRepository.save(planet);
        return planetMapper.fromPlanetToDTO(planet);
    }

    public PlanetDTO getPlanet(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found"));
        return planetMapper.fromPlanetToDTO(planet);
    }

    public PlanetDTO updatePlanet(Long id, PlanetDTO planetDTO) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found"));

        planetMapper.updatePlanetFromDto(planetDTO, planet);

        planet = planetRepository.save(planet);
        return planetMapper.fromPlanetToDTO(planet);
    }

    public void deletePlanet(Long id) {
        if (!planetRepository.existsById(id)) {
            throw new RuntimeException("Faction not found with id: " + id);
        }
        planetRepository.deleteById(id);
    }
}