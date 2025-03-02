package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.LocationDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Mission;
import com.alexkononon.star_wars_project.entity.core.Planet;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "planet", source = "planetId", qualifiedByName = "mapPlanet")
    @Mapping(target = "missions", source = "missionsId", qualifiedByName = "mapMissions")
    @Mapping(target = "livingCharacters", source = "livingCharactersId", qualifiedByName = "mapLivingCharacters")
    @Mapping(target = "stayingCharacters", source = "stayingCharactersId", qualifiedByName = "mapStayingCharacters")
    @Mapping(target = "deleted", ignore = true)
    Location fromDtoToLocation(LocationDTO dto);

    @Mapping(target = "planetId", source = "planet.id")
    @Mapping(target = "missionsId", source = "missions", qualifiedByName = "mapMissionIds")
    @Mapping(target = "livingCharactersId", source = "livingCharacters", qualifiedByName = "mapLivingCharacterIds")
    @Mapping(target = "stayingCharactersId", source = "stayingCharacters", qualifiedByName = "mapStayingCharacterIds")
    LocationDTO fromLocationToDTO(Location location);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "planet", source = "planetId", qualifiedByName = "mapPlanet")
    @Mapping(target = "missions", source = "missionsId", qualifiedByName = "mapMissions")
    @Mapping(target = "livingCharacters", source = "livingCharactersId", qualifiedByName = "mapLivingCharacters")
    @Mapping(target = "stayingCharacters", source = "stayingCharactersId", qualifiedByName = "mapStayingCharacters")
    @Mapping(target = "deleted", ignore = true)
    void updateLocationFromDto(LocationDTO dto, @MappingTarget Location location);

    @Named("mapPlanet")
    default Planet mapPlanet(Long planetId) {
        if (planetId == null) {
            return null;
        }
        Planet planet = new Planet();
        planet.setId(planetId);
        return planet;
    }

    @Named("mapMissions")
    default Set<Mission> mapMissions(Set<Long> missionsId) {
        if (missionsId == null) {
            return null;
        }
        return missionsId.stream().map(id -> {
            Mission mission = new Mission();
            mission.setId(id);
            return mission;
        }).collect(Collectors.toSet());
    }

    @Named("mapLivingCharacters")
    default Set<Character> mapLivingCharacters(Set<Long> livingCharactersId) {
        if (livingCharactersId == null) {
            return null;
        }
        return livingCharactersId.stream().map(id -> {
            Character character = new Character();
            character.setId(id);
            return character;
        }).collect(Collectors.toSet());
    }

    @Named("mapStayingCharacters")
    default Set<Character> mapStayingCharacters(Set<Long> stayingCharactersId) {
        if (stayingCharactersId == null) {
            return null;
        }
        return stayingCharactersId.stream().map(id -> {
            Character character = new Character();
            character.setId(id);
            return character;
        }).collect(Collectors.toSet());
    }

    @Named("mapMissionIds")
    default Set<Long> mapMissionIds(Set<Mission> missions) {
        if (missions == null) {
            return null;
        }
        return missions.stream().map(Mission::getId).collect(Collectors.toSet());
    }

    @Named("mapLivingCharacterIds")
    default Set<Long> mapLivingCharacterIds(Set<Character> livingCharacters) {
        if (livingCharacters == null) {
            return null;
        }
        return livingCharacters.stream().map(Character::getId).collect(Collectors.toSet());
    }

    @Named("mapStayingCharacterIds")
    default Set<Long> mapStayingCharacterIds(Set<Character> stayingCharacters) {
        if (stayingCharacters == null) {
            return null;
        }
        return stayingCharacters.stream().map(Character::getId).collect(Collectors.toSet());
    }
}
