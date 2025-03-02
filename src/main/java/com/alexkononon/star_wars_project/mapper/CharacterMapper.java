package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.entity.core.*;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.enums.EntityType;
import com.alexkononon.star_wars_project.repository.core.*;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {LocationRepository.class, CharacterRepository.class, MissionRepository.class, FactionRepository.class})
public interface CharacterMapper {

    @Mapping(target = "currentLocation", source = "currentLocationId", qualifiedByName = "mapLocation")
    @Mapping(target = "baseLocation", source = "baseLocationId", qualifiedByName = "mapLocation")
    @Mapping(target = "current_xp", source = "currentXp")
    @Mapping(target = "missions", source = "missionsId", qualifiedByName = "mapMissionIds")
    @Mapping(target = "factions", source = "factionsId", qualifiedByName = "mapFactionIds")
    @Mapping(target = "subordinates", source = "subordinatesId", qualifiedByName = "mapSubordinatesIds")
    @Mapping(target = "supreme", source = "supremeId", qualifiedByName = "mapSupreme")
    @Mapping(target = "deleted", ignore = true)
    Character fromDtoToCharacter(CharacterDTO dto, @Context LocationRepository locationRepository, @Context CharacterRepository characterRepository,
                                 @Context MissionRepository missionRepository, @Context FactionRepository factionRepository);

    @Mapping(target = "currentLocationId", source = "currentLocation.id")
    @Mapping(target = "baseLocationId", source = "baseLocation.id")
    @Mapping(target = "currentXp", source = "current_xp")
    @Mapping(target = "missionsId", expression = "java(mapMissionIds(character.getMissions()))")
    @Mapping(target = "factionsId", expression = "java(mapFactionIds(character.getFactions()))")
    @Mapping(target = "subordinatesId", expression = "java(mapSubordinatesIds(character.getSubordinates()))")
    @Mapping(target = "supremeId", expression = "java(character.getSupreme() != null ? character.getSupreme().getId() : null)")
    CharacterDTO fromCharacterToDTO(Character character);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentLocation", source = "currentLocationId", qualifiedByName = "mapLocation")
    @Mapping(target = "baseLocation", source = "baseLocationId", qualifiedByName = "mapLocation")
    @Mapping(target = "current_xp", source = "currentXp")
    @Mapping(target = "missions", source = "missionsId", qualifiedByName = "mapMissionIds")
    @Mapping(target = "factions", source = "factionsId", qualifiedByName = "mapFactionIds")
    @Mapping(target = "subordinates", source = "subordinatesId", qualifiedByName = "mapSubordinatesIds")
    @Mapping(target = "supreme", source = "supremeId", qualifiedByName = "mapSupreme")
    @Mapping(target = "deleted", ignore = true)
    void updateCharacterFromDto(CharacterDTO dto, @MappingTarget Character character, @Context LocationRepository locationRepository,
                                @Context CharacterRepository characterRepository, @Context MissionRepository missionRepository,
                                @Context FactionRepository factionRepository);

    @AfterMapping
    default void setEntityObject(@MappingTarget Character character) {
        if (character.getEntityObject() == null) {
            EntityObject entity = new EntityObject();
            entity.setEntityType(EntityType.character);
            character.setEntityObject(entity);
        }
    }

    @Named("mapLocation")
    default Location mapLocation(Long id, @Context LocationRepository locationRepository) {
        return id == null ? null : locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
    }

    @Named("mapSupreme")
    default Character mapSupreme(Long id, @Context CharacterRepository characterRepository) {
        return id == null ? null : characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));
    }

    @Named("mapMissionIds")
    default Set<Mission> mapMissionIds(Set<Long> missionIds, @Context MissionRepository missionRepository) {
        return missionIds == null ? Set.of() : missionIds.stream()
                .map(id -> missionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Mission not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapFactionIds")
    default Set<Faction> mapFactionIds(Set<Long> factionIds, @Context FactionRepository factionRepository) {
        return factionIds == null ? Set.of() : factionIds.stream()
                .map(id -> factionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapSubordinatesIds")
    default Set<Character> mapSubordinatesIds(Set<Long> subordinatesId, @Context CharacterRepository characterRepository) {
        return subordinatesId == null ? Set.of() : subordinatesId.stream()
                .map(id -> characterRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Subordinate character not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    default Set<Long> mapMissionIds(Set<Mission> missions) {
        return missions.stream().map(Mission::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapFactionIds(Set<Faction> factions) {
        return factions.stream().map(Faction::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapSubordinatesIds(Set<Character> subordinates) {
        return subordinates.stream().map(Character::getId).collect(Collectors.toSet());
    }
}
