package com.alexkononon.star_wars_project.service;

import com.alexkononon.star_wars_project.dto.FactionDTO;
import com.alexkononon.star_wars_project.entity.core.Faction;
import com.alexkononon.star_wars_project.mapper.FactionMapper;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.FactionRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FactionService {

    private final FactionRepository factionRepository;
    private final FactionMapper factionMapper;
    private final CharacterRepository characterRepository;
    private final PlanetRepository planetRepository;

    public FactionService(FactionRepository factionRepository, FactionMapper factionMapper,
                          CharacterRepository characterRepository, PlanetRepository planetRepository) {
        this.factionRepository = factionRepository;
        this.factionMapper = factionMapper;
        this.characterRepository = characterRepository;
        this.planetRepository = planetRepository;
    }

    public FactionDTO createFaction(FactionDTO dto) {
        Faction faction = factionMapper.fromDtoToFaction(dto, factionRepository, planetRepository, characterRepository);
        faction = factionRepository.save(faction);
        return factionMapper.fromFactionToDTO(faction);
    }

    public FactionDTO getFaction(Long id) {
        return factionRepository.findById(id)
                .map(factionMapper::fromFactionToDTO)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id));
    }

    public FactionDTO updateFaction(Long id, FactionDTO dto) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id));

        factionMapper.updateFactionFromDto(dto, faction, factionRepository, planetRepository, characterRepository);
        faction = factionRepository.save(faction);
        return factionMapper.fromFactionToDTO(faction);
    }

    public void deleteFaction(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new RuntimeException("Faction not found with id: " + id);
        }
        factionRepository.deleteById(id);
    }
}
