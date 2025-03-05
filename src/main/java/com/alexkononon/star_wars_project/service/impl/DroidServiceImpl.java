package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.DroidDTO;
import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Droid;
import com.alexkononon.star_wars_project.mapper.DroidMapper;
import com.alexkononon.star_wars_project.repository.core.DroidRepository;
import com.alexkononon.star_wars_project.service.DroidService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class DroidServiceImpl implements DroidService {

    private final DroidRepository droidRepository;
    private final DroidMapper droidMapper;

    public DroidServiceImpl(DroidRepository droidRepository, DroidMapper droidMapper) {
        this.droidRepository = droidRepository;
        this.droidMapper = droidMapper;
    }

    public DroidDTO createDroid(DroidDTO dto) {
        Droid droid = droidMapper.fromDTOToDroid(dto);
        droid = droidRepository.save(droid);
        return droidMapper.fromDroidToDTO(droid);
    }

    public DroidDTO getDroid(Long id) {
        Droid droid = droidRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Droid not found with id: " + id));
        return droidMapper.fromDroidToDTO(droid);
    }

    public List<DroidDTO> getAllDroids() {
        return droidRepository.findAll().stream()
                .map(droidMapper::fromDroidToDTO)
                .collect(Collectors.toList());
    }

    public DroidDTO updateDroid(Long id, DroidDTO dto) {
        Droid droid = droidRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Droid not found with id: " + id));
        droidMapper.updateDroidFromDTO(dto, droid);
        droid = droidRepository.save(droid);
        return droidMapper.fromDroidToDTO(droid);
    }

    public void deleteDroid(Long id) {
        if (!droidRepository.existsById(id)) {
            throw new RuntimeException("Droid not found with id: " + id);
        }
        droidRepository.deleteById(id);
    }
}


