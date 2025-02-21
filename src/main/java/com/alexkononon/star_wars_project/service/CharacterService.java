package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.EntityObject;
import com.alexkononon.star_wars_project.enums.EntityType;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
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

        EntityObject entity = new EntityObject();
        entity.setEntityType(EntityType.character);

        character.setName(characterDTO.getName());
        character.setStatus(characterDTO.getStatus());
        character.setCurrent_xp(characterDTO.getCurrentXp());
        character.setRank(characterDTO.getRank());
        character.setMax_simultaneous_missions(characterDTO.getMaxSimultaneousMissions());
        character.setCompleted_missions(characterDTO.getCompletedMissions());
        character.setDeleted(characterDTO.isDeleted());
        character.setEntityObject(entity);
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

