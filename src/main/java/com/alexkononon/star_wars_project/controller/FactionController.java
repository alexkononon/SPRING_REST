package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.FactionDTO;
import com.alexkononon.star_wars_project.service.impl.FactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factions")
public class FactionController {

    private final FactionServiceImpl factionServiceImpl;

    public FactionController(FactionServiceImpl factionServiceImpl) {
        this.factionServiceImpl = factionServiceImpl;
    }

    @PostMapping
    public ResponseEntity<FactionDTO> createFaction(@RequestBody FactionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(factionServiceImpl.createFaction(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFaction(@PathVariable Long id) {
        return ResponseEntity.ok(factionServiceImpl.getFaction(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(@PathVariable Long id, @RequestBody FactionDTO dto) {
        return ResponseEntity.ok(factionServiceImpl.updateFaction(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaction(@PathVariable Long id) {
        factionServiceImpl.deleteFaction(id);
        return ResponseEntity.noContent().build();
    }
}
