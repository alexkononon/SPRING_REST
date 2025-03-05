package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.FactionDTO;
import com.alexkononon.star_wars_project.entity.core.Faction;
import com.alexkononon.star_wars_project.mapper.FactionMapper;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.FactionRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import com.alexkononon.star_wars_project.service.FactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class FactionServiceImpl implements FactionService {

    private final FactionRepository factionRepository;
    private final FactionMapper factionMapper;
    private final CharacterRepository characterRepository;
    private final PlanetRepository planetRepository;
    private final SmsServiceImpl smsServiceImpl;

    public FactionServiceImpl(FactionRepository factionRepository, FactionMapper factionMapper,
                              CharacterRepository characterRepository, PlanetRepository planetRepository, SmsServiceImpl smsServiceImpl) {
        this.factionRepository = factionRepository;
        this.factionMapper = factionMapper;
        this.characterRepository = characterRepository;
        this.planetRepository = planetRepository;
        this.smsServiceImpl = smsServiceImpl;
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

    public List<FactionDTO> getAllFactions() {
        return factionRepository.findAll().stream()
                .map(factionMapper::fromFactionToDTO)
                .collect(Collectors.toList());
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

    public FactionDTO startConflictBetween(Long factionId, Long targetFactionId) {
        Faction faction = factionRepository.findById(factionId)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + factionId));
        Faction targetFaction = factionRepository.findById(targetFactionId)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + targetFactionId));

        if (faction.getEnemyFactions().contains(targetFaction)) {
            throw new RuntimeException("These factions are already in conflict.");
        }

        faction.getEnemyFactions().add(targetFaction);

        factionRepository.save(faction);

        smsServiceImpl.sendSms("+48111111111", "The " + faction.getName() + " faction declared war on us )");

        return factionMapper.fromFactionToDTO(faction);
    }

    public List<FactionDTO> getConflictingFactions(Long factionId) {
        Faction faction = factionRepository.findById(factionId)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + factionId));

        Set<Faction> conflicts = new HashSet<>();
        if (faction.getEnemyFactions() != null) {
            conflicts.addAll(faction.getEnemyFactions());
        }
        if (faction.getEnemyOf() != null) {
            conflicts.addAll(faction.getEnemyOf());
        }

        return conflicts.stream()
                .map(factionMapper::fromFactionToDTO)
                .collect(Collectors.toList());
    }

}

