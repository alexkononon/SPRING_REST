package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.CharacterDTO;
import com.alexkononon.star_wars_project.entity.core.Character;
import com.alexkononon.star_wars_project.entity.core.EntityObject;
import com.alexkononon.star_wars_project.entity.core.Location;
import com.alexkononon.star_wars_project.enums.EntityType;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(target = "currentLocation", source = "currentLocationId")
    @Mapping(target = "current_xp", source = "currentXp")
    Character fromDtoToCharacter(CharacterDTO dto, @Context LocationRepository locationRepository);

    @Mapping(target = "currentLocationId", source = "currentLocation.id")
    @Mapping(target = "currentXp", source = "current_xp")
    CharacterDTO fromCharacterToDTO(Character character);


    @AfterMapping
    default void setEntityObject(@MappingTarget Character character) {
        if (character.getEntityObject() == null) {
            EntityObject entity = new EntityObject();
            entity.setEntityType(EntityType.character);
            character.setEntityObject(entity);
        }
    }

    default Location map(Long id, @Context LocationRepository locationRepository) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }
}