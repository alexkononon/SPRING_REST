package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.MissionDTO;
import com.alexkononon.star_wars_project.service.MissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<MissionDTO> createMission(@RequestBody MissionDTO missionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionService.createMission(missionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionDTO> getMission(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.getMission(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionDTO> updateMission(@PathVariable Long id, @RequestBody MissionDTO missionDTO) {
        return ResponseEntity.ok(missionService.updateMission(id, missionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}

