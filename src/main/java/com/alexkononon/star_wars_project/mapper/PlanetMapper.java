package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Faction;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Planet;
import com.alexkononon.star_wars_project.repository.core.FactionRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {FactionRepository.class, LocationRepository.class})
public interface PlanetMapper {

    @Mapping(target = "faction", source = "factionId", qualifiedByName = "mapFaction")
    @Mapping(target = "locations", source = "locationIds", qualifiedByName = "mapToLocations")
    @Mapping(target = "deleted", ignore = true)
    Planet fromDtoToPlanet(PlanetDTO dto, @Context FactionRepository factionRepository,
                           @Context LocationRepository locationRepository);

    @Mapping(target = "factionId", source = "faction.id")
    @Mapping(target = "locationIds", expression = "java(mapToLocationIds(planet.getLocations()))")
    PlanetDTO fromPlanetToDTO(Planet planet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "faction", source = "factionId", qualifiedByName = "mapFaction")
    @Mapping(target = "locations", source = "locationIds", qualifiedByName = "mapToLocations")
    @Mapping(target = "deleted", ignore = true)
    void updatePlanetFromDto(PlanetDTO dto, @MappingTarget Planet planet,
                             @Context FactionRepository factionRepository,
                             @Context LocationRepository locationRepository);

    @Named("mapFaction")
    default Faction mapFaction(Long factionId, @Context FactionRepository factionRepository) {
        return factionId == null ? null : factionRepository.findById(factionId)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + factionId));
    }

    @Named("mapToLocations")
    default Set<Location> mapToLocations(Set<Long> ids, @Context LocationRepository locationRepository) {
        return ids == null ? Set.of() : ids.stream()
                .map(id -> locationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Location not found with id: " + id)))
                .collect(Collectors.toSet());
    }

    @Named("mapToLocationIds")
    default Set<Long> mapToLocationIds(Set<Location> locations) {
        return locations == null ? Set.of() : locations.stream()
                .map(Location::getId)
                .collect(Collectors.toSet());
    }
}
