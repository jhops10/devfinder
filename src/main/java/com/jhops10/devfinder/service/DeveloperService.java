package com.jhops10.devfinder.service;

import com.jhops10.devfinder.domain.Developer;
import com.jhops10.devfinder.domain.Project;
import com.jhops10.devfinder.dto.DeveloperRequestDTO;
import com.jhops10.devfinder.dto.DeveloperResponseDTO;
import com.jhops10.devfinder.dto.ProjectDTO;
import com.jhops10.devfinder.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperResponseDTO createDeveloper(DeveloperRequestDTO dto) {
        Developer developer = Developer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .location(dto.getLocation())
                .skills(dto.getSkills())
                .bio(dto.getBio())
                .available(dto.getAvailable())
                .projects(mapToProjects(dto.getProjects()))
                .createdAt(Instant.now())
                .build();

        Developer saved = developerRepository.save(developer);
        return mapToResponseDTO(saved);

    }

    public List<DeveloperResponseDTO> getAvailableDevelopers() {
        return developerRepository.findByAvailableTrue()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public List<DeveloperResponseDTO> getDevelopersBySkill(String skill) {
        return developerRepository.findBySkillsContainingIgnoreCase(skill)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public List<DeveloperResponseDTO> getDevelopersByLocation(String location) {
        return developerRepository.findByLocationIgnoreCase(location)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public List<DeveloperResponseDTO> getAll() {
        return developerRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }


    private DeveloperResponseDTO mapToResponseDTO(Developer developer) {
        return DeveloperResponseDTO.builder()
                .id(developer.getId())
                .name(developer.getName())
                .email(developer.getEmail())
                .location(developer.getLocation())
                .skills(developer.getSkills())
                .bio(developer.getBio())
                .available(developer.getAvailable())
                .projects(mapToProjectDTOs(developer.getProjects()))
                .createdAt(developer.getCreatedAt())
                .build();
    }

    private List<ProjectDTO> mapToProjectDTOs(List<Project> projects) {
        return projects == null ? List.of() : projects.stream()
                .map(p -> new ProjectDTO(p.getName(), p.getUrl(), p.getDescription()))
                .toList();
    }

    private List<Project> mapToProjects(List<ProjectDTO> dtos) {
        return dtos == null ? List.of() : dtos.stream()
                .map(p -> new Project(p.getName(), p.getUrl(), p.getDescription()))
                .toList();
    }
}
