package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.LocationDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Mission;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import com.alexkononon.star_wars_project.repository.core.MissionRepository;
import com.alexkononon.star_wars_project.repository.core.PlanetRepository;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {LocationRepository.class, PlanetRepository.class, MissionRepository.class, CharacterRepository.class})
public interface LocationMapper {

    @Mapping(target = "planet", source = "planetId", qualifiedByName = "mapToPlanet")
    @Mapping(target = "missions", source = "missionsId", qualifiedByName = "mapToMissions")
    @Mapping(target = "livingCharacters", source = "livingCharactersId", qualifiedByName = "mapToCharacters")
    @Mapping(target = "stayingCharacters", source = "stayingCharactersId", qualifiedByName = "mapToCharacters")
    @Mapping(target = "deleted", ignore = true)
    Location fromDtoToLocation(LocationDTO dto, @Context PlanetRepository planetRepository,
                               @Context MissionRepository missionRepository, @Context CharacterRepository characterRepository);

    @Mapping(target = "planetId", source = "planet.id")
    @Mapping(target = "missionsId", expression = "java(mapMissionIds(location.getMissions()))")
    @Mapping(target = "livingCharactersId", expression = "java(mapCharacterIds(location.getLivingCharacters()))")
    @Mapping(target = "stayingCharactersId", expression = "java(mapCharacterIds(location.getStayingCharacters()))")
    LocationDTO fromLocationToDTO(Location location);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "planet", source = "planetId", qualifiedByName = "mapToPlanet")
    @Mapping(target = "missions", source = "missionsId", qualifiedByName = "mapToMissions")
    @Mapping(target = "livingCharacters", source = "livingCharactersId", qualifiedByName = "mapToCharacters")
    @Mapping(target = "stayingCharacters", source = "stayingCharactersId", qualifiedByName = "mapToCharacters")
    @Mapping(target = "deleted", ignore = true)
    void updateLocationFromDto(LocationDTO dto, @MappingTarget Location location,
                               @Context PlanetRepository planetRepository,
                               @Context MissionRepository missionRepository,
                               @Context CharacterRepository characterRepository);

    @Named("mapToPlanet")
    default Planet mapToPlanet(Long planetId, @Context PlanetRepository planetRepository) {
        return planetId == null ? null : planetRepository.findById(planetId)
                .orElseThrow(() -> new RuntimeException("Planet not found with id: " + planetId));
    }

    @Named("mapToMissions")
    default Set<Mission> mapToMissions(Set<Long> missionsId, @Context MissionRepository missionRepository) {
        return missionsId == null ? Set.of() : missionsId.stream()
                .map(id -> missionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Mission not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapToCharacters")
    default Set<Character> mapToCharacters(Set<Long> characterIds, @Context CharacterRepository characterRepository) {
        return characterIds == null ? Set.of() : characterIds.stream()
                .map(id -> characterRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Character not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    default Set<Long> mapMissionIds(Set<Mission> missions) {
        return missions == null ? Set.of() : missions.stream()
                .map(Mission::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapCharacterIds(Set<Character> characters) {
        return characters == null ? Set.of() : characters.stream()
                .map(Character::getId).collect(Collectors.toSet());
    }
}

