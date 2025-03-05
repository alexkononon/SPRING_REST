package com.alexkononon.star_wars_project.service.impl;

import com.alexkononon.star_wars_project.dto.MissionDTO;
import com.alexkononon.star_wars_project.dto.PlanetDTO;
import com.alexkononon.star_wars_project.entity.core.Mission;
import com.alexkononon.star_wars_project.mapper.MissionMapper;
import com.alexkononon.star_wars_project.repository.core.MissionRepository;
import com.alexkononon.star_wars_project.repository.core.CharacterRepository;
import com.alexkononon.star_wars_project.repository.core.LocationRepository;
import com.alexkononon.star_wars_project.repository.core.MissionStatusHistoryRepository;
import com.alexkononon.star_wars_project.service.MissionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final MissionMapper missionMapper;
    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;
    private final MissionStatusHistoryRepository missionStatusHistoryRepository;

    public MissionServiceImpl(MissionRepository missionRepository, MissionMapper missionMapper,
                              CharacterRepository characterRepository, LocationRepository locationRepository,
                              MissionStatusHistoryRepository missionStatusHistoryRepository) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
        this.characterRepository = characterRepository;
        this.locationRepository = locationRepository;
        this.missionStatusHistoryRepository = missionStatusHistoryRepository;
    }

    public MissionDTO createMission(MissionDTO dto) {
        Mission mission = missionMapper.fromDtoToMission(dto, characterRepository, locationRepository, missionStatusHistoryRepository);
        mission = missionRepository.save(mission);
        return missionMapper.fromMissionToDTO(mission);
    }

    public MissionDTO getMission(Long id) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission not found with id: " + id));
        return missionMapper.fromMissionToDTO(mission);
    }

    public List<MissionDTO> getAllMissions() {
        return missionRepository.findAll().stream()
                .map(missionMapper::fromMissionToDTO)
                .collect(Collectors.toList());
    }

    public MissionDTO updateMission(Long id, MissionDTO dto) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mission not found with id: " + id));
        missionMapper.updateMissionFromDto(dto, mission, characterRepository, locationRepository, missionStatusHistoryRepository);
        mission = missionRepository.save(mission);
        return missionMapper.fromMissionToDTO(mission);
    }

    public void deleteMission(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new RuntimeException("Mission not found with id: " + id);
        }
        missionRepository.deleteById(id);
    }
}

