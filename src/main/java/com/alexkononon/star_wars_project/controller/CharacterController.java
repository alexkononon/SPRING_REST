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

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody CharacterDTO characterDTO) {
        CharacterDTO createdCharacter = characterService.createCharacter(characterDTO);
        return new ResponseEntity<>(createdCharacter, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable Long id) {
        CharacterDTO character = characterService.getCharacter(id);
        return ResponseEntity.ok(character);
    }
}

