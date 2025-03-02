package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Faction;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.entity.core.Planet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PlanetMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "faction", source = "factionId", qualifiedByName = "mapFaction")
    @Mapping(target = "locations", source = "locations", qualifiedByName = "mapToLocations")
    @Mapping(target = "deleted", ignore = true)
    Planet fromDtoToPlanet(PlanetDTO dto);

    @Mapping(target = "factionId", source = "faction.id")
    @Mapping(target = "locations", source = "locations", qualifiedByName = "mapToLocationIds")
    PlanetDTO fromPlanetToDTO(Planet planet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "faction", source = "factionId", qualifiedByName = "mapFaction")
    @Mapping(target = "locations", source = "locations", qualifiedByName = "mapToLocations")
    @Mapping(target = "deleted", ignore = true)
    void updatePlanetFromDto(PlanetDTO dto, @MappingTarget Planet planet);

    @Named("mapFaction")
    default Faction mapFaction(Long factionId) {
        if (factionId == null) {
            return null;
        }
        Faction faction = new Faction();
        faction.setId(factionId);
        return faction;
    }

    @Named("mapToLocations")
    default Set<Location> mapToLocations(Set<Long> ids) {
        if (ids == null) {
            return null;
        }
        return ids.stream()
                .map(id -> {
                    Location location = new Location();
                    location.setId(id);
                    return location;
                })
                .collect(Collectors.toSet());
    }

    @Named("mapToLocationIds")
    default Set<Long> mapToLocationIds(Set<Location> locations) {
        if (locations == null) {
            return null;
        }
        return locations.stream()
                .map(Location::getId)
                .collect(Collectors.toSet());
    }
}