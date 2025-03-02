package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.FactionDTO;
import com.alexkononon.star_wars_project.service.FactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @PostMapping
    public ResponseEntity<FactionDTO> createFaction(@RequestBody FactionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(factionService.createFaction(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFaction(@PathVariable Long id) {
        return ResponseEntity.ok(factionService.getFaction(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(@PathVariable Long id, @RequestBody FactionDTO dto) {
        return ResponseEntity.ok(factionService.updateFaction(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFaction(@PathVariable Long id) {
        factionService.deleteFaction(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
