package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

        private final PlanetService planetService;

        public PlanetController(PlanetService planetService) {
            this.planetService = planetService;
        }

        @PostMapping
        public ResponseEntity<HttpStatus> createPlanet(@RequestBody PlanetDTO planetDTO) {
            planetService.createPlanet(planetDTO);
            return ResponseEntity.ok(HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<PlanetDTO> getPlanet(@PathVariable Long id) {
            return ResponseEntity.ok(planetService.getPlanet(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<HttpStatus> updatePlanet(@PathVariable Long id, @RequestBody PlanetDTO planetDTO) {
            planetService.updatePlanet(id, planetDTO);
            return ResponseEntity.ok(HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> deletePlanet(@PathVariable Long id) {
            planetService.deletePlanet(id);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

