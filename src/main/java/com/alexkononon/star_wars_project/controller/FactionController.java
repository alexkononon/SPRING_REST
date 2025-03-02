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
        FactionDTO created = factionService.createFaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFaction(@PathVariable Long id) {
        return ResponseEntity.ok(factionService.getFaction(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(@PathVariable Long id, @RequestBody FactionDTO dto) {
        FactionDTO updated = factionService.updateFaction(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaction(@PathVariable Long id) {
        factionService.deleteFaction(id);
        return ResponseEntity.noContent().build();
    }
}
