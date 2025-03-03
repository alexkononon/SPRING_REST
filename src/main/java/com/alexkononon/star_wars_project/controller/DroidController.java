package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.DroidDTO;
import com.alexkononon.star_wars_project.service.impl.DroidServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/droids")
public class DroidController {

    private final DroidServiceImpl droidServiceImpl;

    public DroidController(DroidServiceImpl droidServiceImpl) {
        this.droidServiceImpl = droidServiceImpl;
    }

    @PostMapping
    public ResponseEntity<DroidDTO> createDroid(@RequestBody DroidDTO droidDTO) {
        return ResponseEntity.ok(droidServiceImpl.createDroid(droidDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroidDTO> getDroid(@PathVariable Long id) {
        return ResponseEntity.ok(droidServiceImpl.getDroid(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DroidDTO> updateDroid(@PathVariable Long id, @RequestBody DroidDTO droidDTO) {
        return ResponseEntity.ok(droidServiceImpl.updateDroid(id, droidDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDroid(@PathVariable Long id) {
        droidServiceImpl.deleteDroid(id);
        return ResponseEntity.noContent().build();
    }
}

