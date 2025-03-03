package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.service.impl.PlanetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    private final PlanetServiceImpl planetServiceImpl;

    public PlanetController(PlanetServiceImpl planetServiceImpl) {
        this.planetServiceImpl = planetServiceImpl;
    }

    @PostMapping
    public ResponseEntity<PlanetDTO> createPlanet(@RequestBody PlanetDTO planetDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planetServiceImpl.createPlanet(planetDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanetDTO> getPlanet(@PathVariable Long id) {
        return ResponseEntity.ok(planetServiceImpl.getPlanet(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetDTO> updatePlanet(@PathVariable Long id, @RequestBody PlanetDTO planetDTO) {
        return ResponseEntity.ok(planetServiceImpl.updatePlanet(id, planetDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        planetServiceImpl.deletePlanet(id);
        return ResponseEntity.noContent().build();
    }
}

