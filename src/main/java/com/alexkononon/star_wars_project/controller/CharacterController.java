package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public ResponseEntity<HttpStatus> createCharacter(@RequestBody CharacterDTO characterDTO) {
        characterService.createCharacter(characterDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable Long id) {
        return ResponseEntity.ok(characterService.getCharacter(id));
    }
}

