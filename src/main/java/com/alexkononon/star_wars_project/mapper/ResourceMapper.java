package com.alexkononon.star_wars_project.mapper;

import com.alexkononon.star_wars_project.dto.ResourceDTO;
import com.alexkononon.star_wars_project.entity.core.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    ResourceDTO resourceToResourceDTO(Resource resource);

    Resource resourceDTOToResource(ResourceDTO resourceDTO);

    void updateResourceFromDTO(ResourceDTO resourceDTO, @MappingTarget Resource resource);
}
