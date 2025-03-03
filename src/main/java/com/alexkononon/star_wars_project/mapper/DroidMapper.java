package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.DroidDTO;
import com.alexkononon.star_wars_project.entity.core.Droid;
import com.alexkononon.star_wars_project.entity.core.EntityObject;
import com.alexkononon.star_wars_project.enums.DroidSeries;
import com.alexkononon.star_wars_project.enums.EntityType;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DroidMapper {

    @Mapping(target = "series", source = "series", qualifiedByName = "droidSeriesToString")
    DroidDTO fromDroidToDTO(Droid droid);

    @Mapping(target = "series", source = "series", qualifiedByName = "stringToDroidSeries")

    @Mapping(target = "entityObject", ignore = true)
    Droid fromDTOToDroid(DroidDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "entityObject", ignore = true)
    @Mapping(target = "series", source = "series", qualifiedByName = "stringToDroidSeries")
    void updateDroidFromDTO(DroidDTO dto, @MappingTarget Droid droid);

    @AfterMapping
    default void setEntityObject(@MappingTarget Droid droid) {
        if (droid.getEntityObject() == null) {
            EntityObject entity = new EntityObject();
            entity.setEntityType(EntityType.droid);
            droid.setEntityObject(entity);
        }
    }

    @Named("droidSeriesToString")
    default String droidSeriesToString(DroidSeries series) {
        return series != null ? series.name() : null;
    }

    @Named("stringToDroidSeries")
    default DroidSeries stringToDroidSeries(String series) {
        return series != null ? DroidSeries.valueOf(series) : null;
    }
}
