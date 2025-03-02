package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.MissionDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Mission;
import com.alexkononon.star_wars_project.entity.core.MissionStatusHistory;
import com.alexkononon.star_wars_project.enums.Status;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import com.alexkononon.star_wars_project.repository.core.MissionStatusHistoryRepository;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CharacterRepository.class, LocationRepository.class, MissionStatusHistoryRepository.class})
public interface MissionMapper {

    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatusToString")
    @Mapping(target = "participantsId", expression = "java(mapCharacterIds(mission.getParticipants()))")
    @Mapping(target = "missionStatusHistoriesId", expression = "java(mapMissionStatusHistoryIds(mission.getMission_status_histories()))")
    @Mapping(target = "locationsId", expression = "java(mapLocationIds(mission.getLocations()))")
    MissionDTO fromMissionToDTO(Mission mission);

    @Mapping(target = "status", source = "status", qualifiedByName = "mapStringToStatus")
    @Mapping(target = "participants", source = "participantsId", qualifiedByName = "mapToCharacters")
    @Mapping(target = "mission_status_histories", source = "missionStatusHistoriesId", qualifiedByName = "mapToMissionStatusHistories")
    @Mapping(target = "locations", source = "locationsId", qualifiedByName = "mapToLocations")
    Mission fromDtoToMission(MissionDTO dto, @Context CharacterRepository characterRepository,
                             @Context LocationRepository locationRepository,
                             @Context MissionStatusHistoryRepository missionStatusHistoryRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStringToStatus")
    @Mapping(target = "participants", source = "participantsId", qualifiedByName = "mapToCharacters")
    @Mapping(target = "mission_status_histories", source = "missionStatusHistoriesId", qualifiedByName = "mapToMissionStatusHistories")
    @Mapping(target = "locations", source = "locationsId", qualifiedByName = "mapToLocations")
    void updateMissionFromDto(MissionDTO dto, @MappingTarget Mission mission,
                              @Context CharacterRepository characterRepository,
                              @Context LocationRepository locationRepository,
                              @Context MissionStatusHistoryRepository missionStatusHistoryRepository);

    @Named("mapStringToStatus")
    default Status mapStringToStatus(String status) {
        return status != null ? Status.valueOf(status) : null;
    }

    @Named("mapStatusToString")
    default String mapStatusToString(Status status) {
        return status != null ? status.name() : null;
    }

    @Named("mapToCharacters")
    default Set<Character> mapToCharacters(Set<Long> ids, @Context CharacterRepository characterRepository) {
        return ids == null ? Set.of() : ids.stream()
                .map(id -> characterRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Character not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapToMissionStatusHistories")
    default Set<MissionStatusHistory> mapToMissionStatusHistories(Set<Long> ids, @Context MissionStatusHistoryRepository missionStatusHistoryRepository) {
        return ids == null ? Set.of() : ids.stream()
                .map(id -> missionStatusHistoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("MissionStatusHistory not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapToLocations")
    default Set<Location> mapToLocations(Set<Long> ids, @Context LocationRepository locationRepository) {
        return ids == null ? Set.of() : ids.stream()
                .map(id -> locationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Location not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    default Set<Long> mapCharacterIds(Set<Character> characters) {
        return characters.stream().map(Character::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapMissionStatusHistoryIds(Set<MissionStatusHistory> missionStatusHistories) {
        return missionStatusHistories.stream().map(MissionStatusHistory::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapLocationIds(Set<Location> locations) {
        return locations.stream().map(Location::getId).collect(Collectors.toSet());
    }
}

