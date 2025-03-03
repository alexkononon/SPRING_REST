package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.service.impl.CharacterServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterServiceImpl characterServiceImpl;

    public CharacterController(CharacterServiceImpl characterServiceImpl) {
        this.characterServiceImpl = characterServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.ok(characterServiceImpl.createCharacter(characterDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable Long id) {
        return ResponseEntity.ok(characterServiceImpl.getCharacter(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO characterDTO) {
        return ResponseEntity.ok(characterServiceImpl.updateCharacter(id, characterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterServiceImpl.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}

