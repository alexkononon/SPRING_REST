package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.ResourceDTO;
import com.alexkononon.star_wars_project.entity.core.Resource;
import com.alexkononon.star_wars_project.mapper.ResourceMapper;
import com.alexkononon.star_wars_project.repository.core.ResourceRepository;
import com.alexkononon.star_wars_project.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public ResourceServiceImpl(ResourceRepository resourceRepository, ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
    }

    public ResourceDTO createResource(ResourceDTO resourceDTO) {
        Resource resource = resourceMapper.resourceDTOToResource(resourceDTO);
        resource = resourceRepository.save(resource);
        return resourceMapper.resourceToResourceDTO(resource);
    }

    public ResourceDTO getResource(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + id));
        return resourceMapper.resourceToResourceDTO(resource);
    }

    public List<ResourceDTO> getAllResources() {
        return resourceRepository.findAll().stream()
                .map(resourceMapper::resourceToResourceDTO)
                .collect(Collectors.toList());
    }

    public ResourceDTO updateResource(Long id, ResourceDTO resourceDTO) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + id));
        resourceMapper.updateResourceFromDTO(resourceDTO, resource);
        resource = resourceRepository.save(resource);
        return resourceMapper.resourceToResourceDTO(resource);
    }

    public void deleteResource(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new RuntimeException("Resource not found with id: " + id);
        }
        resourceRepository.deleteById(id);
    }
}

