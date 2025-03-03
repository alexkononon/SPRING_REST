package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.MissionDTO;
import com.alexkononon.star_wars_project.service.impl.MissionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionServiceImpl missionServiceImpl;

    public MissionController(MissionServiceImpl missionServiceImpl) {
        this.missionServiceImpl = missionServiceImpl;
    }

    @PostMapping
    public ResponseEntity<MissionDTO> createMission(@RequestBody MissionDTO missionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionServiceImpl.createMission(missionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionDTO> getMission(@PathVariable Long id) {
        return ResponseEntity.ok(missionServiceImpl.getMission(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionDTO> updateMission(@PathVariable Long id, @RequestBody MissionDTO missionDTO) {
        return ResponseEntity.ok(missionServiceImpl.updateMission(id, missionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        missionServiceImpl.deleteMission(id);
        return ResponseEntity.noContent().build();
    }
}

