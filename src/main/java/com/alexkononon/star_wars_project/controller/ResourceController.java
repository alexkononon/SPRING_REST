package com.alexkononon.star_wars_project.controller;

import com.alexkononon.star_wars_project.dto.ResourceDTO;
import com.alexkononon.star_wars_project.service.impl.ResourceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceServiceImpl resourceServiceImpl;

    public ResourceController(ResourceServiceImpl resourceServiceImpl) {
        this.resourceServiceImpl = resourceServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resourceServiceImpl.createResource(resourceDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResource(@PathVariable Long id) {
        return ResponseEntity.ok(resourceServiceImpl.getResource(id));
    }

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources() {
        return ResponseEntity.ok(resourceServiceImpl.getAllResources());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceDTO> updateResource(@PathVariable Long id, @RequestBody ResourceDTO resourceDTO) {
        return ResponseEntity.ok(resourceServiceImpl.updateResource(id, resourceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourceServiceImpl.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}
