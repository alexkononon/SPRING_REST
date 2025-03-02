package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.mapper.CharacterMapper;
import com.alexkononon.star_wars_project.repository.core.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CharacterService {

    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;
    private final MissionRepository missionRepository;
    private final FactionRepository factionRepository;

    public CharacterService(CharacterMapper characterMapper, CharacterRepository characterRepository, LocationRepository locationRepository,
                            MissionRepository missionRepository, FactionRepository factionRepository) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
        this.locationRepository = locationRepository;
        this.missionRepository = missionRepository;
        this.factionRepository = factionRepository;
    }

    public CharacterDTO createCharacter(CharacterDTO characterDTO) {
        Character character = characterMapper.fromDtoToCharacter(characterDTO, locationRepository, characterRepository, missionRepository, factionRepository);
        character = characterRepository.save(character);
        return characterMapper.fromCharacterToDTO(character);
    }

    public CharacterDTO getCharacter(Long id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        return characterMapper.fromCharacterToDTO(character);
    }

    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        characterMapper.updateCharacterFromDto(characterDTO, character, locationRepository, characterRepository, missionRepository, factionRepository);
        character = characterRepository.save(character);
        return characterMapper.fromCharacterToDTO(character);
    }

    public void deleteCharacter(Long id) {
        if (!characterRepository.existsById(id)) {
            throw new RuntimeException("Faction not found with id: " + id);
        }
        characterRepository.deleteById(id);
    }
}
