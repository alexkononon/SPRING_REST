package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.Faction;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.mapper.CharacterMapper;
import com.alexkononon.star_wars_project.repository.core.*;
import com.alexkononon.star_wars_project.service.CharacterService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;
    private final MissionRepository missionRepository;
    private final FactionRepository factionRepository;

    public CharacterServiceImpl(CharacterMapper characterMapper, CharacterRepository characterRepository, LocationRepository locationRepository,
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
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));
        return characterMapper.fromCharacterToDTO(character);
    }

    public List<CharacterDTO> getAllCharacters() {
        return characterRepository.findAll().stream()
                .map(characterMapper::fromCharacterToDTO)
                .collect(Collectors.toList());
    }

    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));
        characterMapper.updateCharacterFromDto(characterDTO, character, locationRepository, characterRepository, missionRepository, factionRepository);
        character = characterRepository.save(character);
        return characterMapper.fromCharacterToDTO(character);
    }

    public void deleteCharacter(Long id) {
        if (!characterRepository.existsById(id)) {
            throw new RuntimeException("Character not found with id: " + id);
        }
        characterRepository.deleteById(id);
    }

    public void changeCurrentLocation(Long characterId, Long newLocationId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found: " + characterId));
        Location newLocation = locationRepository.findById(newLocationId)
                .orElseThrow(() -> new RuntimeException("Location not found: " + newLocationId));

        character.setCurrentLocation(newLocation);
        characterRepository.save(character);
    }

    public void changeHomeLocation(Long characterId, Long newLocationId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found: " + characterId));
        Location newLocation = locationRepository.findById(newLocationId)
                .orElseThrow(() -> new RuntimeException("Location not found: " + newLocationId));

        if (newLocation.getPlanet() != null) {
            Planet planet = newLocation.getPlanet();

            if (planet.getFaction() != null) {
                Faction planetFaction = planet.getFaction();

                Set<Faction> characterFactions = character.getFactions();
                if (characterFactions != null && !characterFactions.isEmpty()) {
                    boolean hostile = characterFactions.stream()
                            .anyMatch(f -> f.getEnemyFactions().contains(planetFaction));

                    if (hostile) {
                        throw new RuntimeException(
                                "Cannot change current location: the planet's faction is hostile to one of the character's factions"
                        );
                    }
                }}

        }
        character.setBaseLocation(newLocation);
        characterRepository.save(character);
    }
}
