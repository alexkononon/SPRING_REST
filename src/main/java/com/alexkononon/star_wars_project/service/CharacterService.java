package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.entity.Character;
import com.alexkononon.star_wars_project.entity.StarWarsEntity;
import com.alexkononon.star_wars_project.enums.EntityType;
import com.alexkononon.star_wars_project.repository.CharacterRepository;
import com.alexkononon.star_wars_project.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, LocationRepository locationRepository) {
        this.characterRepository = characterRepository;
        this.locationRepository = locationRepository;
    }

    public CharacterDTO createCharacter(CharacterDTO characterDTO) {
        Character character = new Character();

        StarWarsEntity entity = new StarWarsEntity();
        entity.setEntityType(EntityType.character);

        character.setName(characterDTO.getName());
        character.setStatus(characterDTO.getStatus());
        character.setCurrent_xp(characterDTO.getCurrentXp());
        character.setRank(characterDTO.getRank());
        character.setMax_simultaneous_missions(characterDTO.getMaxSimultaneousMissions());
        character.setCompleted_missions(characterDTO.getCompletedMissions());
        character.setDeleted(characterDTO.isDeleted());
        character.setStarWarsEntity(entity);
        character.setCurrentLocation(locationRepository.findById(characterDTO.getCurrentLocationId())
                .orElseThrow( () -> new RuntimeException("Location not found")));

        character = characterRepository.save(character);
        return new CharacterDTO(character);
    }

    public CharacterDTO getCharacter(Long id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        return new CharacterDTO(character);
    }

}

