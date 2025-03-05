package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.FactionDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.Faction;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.FactionRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import org.mapstruct.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {FactionRepository.class, PlanetRepository.class, CharacterRepository.class})
public interface FactionMapper {

    @Mapping(target = "enemyFactionIds", expression = "java(mapAllEnemyFactionIds(faction))")
    @Mapping(target = "planetIds", expression = "java(mapPlanetIds(faction.getPlanets()))")
    @Mapping(target = "memberIds", expression = "java(mapCharacterIds(faction.getMembers()))")
    FactionDTO fromFactionToDTO(Faction faction);

    @Mapping(target = "enemyFactions", source = "enemyFactionIds", qualifiedByName = "mapToFactions")
    @Mapping(target = "planets", source = "planetIds", qualifiedByName = "mapToPlanets")
    @Mapping(target = "members", source = "memberIds", qualifiedByName = "mapToCharacters")
    Faction fromDtoToFaction(FactionDTO dto, @Context FactionRepository factionRepository,
                             @Context PlanetRepository planetRepository,
                             @Context CharacterRepository characterRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enemyFactions", source = "enemyFactionIds", qualifiedByName = "mapToFactions")
    @Mapping(target = "planets", source = "planetIds", qualifiedByName = "mapToPlanets")
    @Mapping(target = "members", source = "memberIds", qualifiedByName = "mapToCharacters")
    void updateFactionFromDto(FactionDTO dto, @MappingTarget Faction faction,
                              @Context FactionRepository factionRepository,
                              @Context PlanetRepository planetRepository,
                              @Context CharacterRepository characterRepository);

    @Named("mapToFactions")
    default Set<Faction> mapToFactions(Set<Long> ids, @Context FactionRepository factionRepository) {
        return ids == null ? Set.of() : ids.stream()
                .map(id -> factionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapToPlanets")
    default Set<Planet> mapToPlanets(Set<Long> ids, @Context PlanetRepository planetRepository) {
        return ids == null ? Set.of() : ids.stream()
                .map(id -> planetRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Planet not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapToCharacters")
    default Set<Character> mapToCharacters(Set<Long> ids, @Context CharacterRepository characterRepository) {
        return ids == null ? Set.of() : ids.stream()
                .map(id -> characterRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Character not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    default Set<Long> mapAllEnemyFactionIds(Faction faction) {
        Set<Long> enemyIds = new HashSet<>();
        if (faction.getEnemyFactions() != null) {
            enemyIds.addAll(faction.getEnemyFactions().stream()
                    .map(Faction::getId)
                    .collect(Collectors.toSet()));
        }
        if (faction.getEnemyOf() != null) {
            enemyIds.addAll(faction.getEnemyOf().stream()
                    .map(Faction::getId)
                    .collect(Collectors.toSet()));
        }
        return enemyIds;
    }

    default Set<Long> mapFactionIds(Set<Faction> factions) {
        return factions.stream().map(Faction::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapPlanetIds(Set<Planet> planets) {
        return planets.stream().map(Planet::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapCharacterIds(Set<Character> characters) {
        return characters.stream().map(Character::getId).collect(Collectors.toSet());
    }

}
