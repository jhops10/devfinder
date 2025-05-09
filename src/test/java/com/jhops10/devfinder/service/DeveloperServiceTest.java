package com.jhops10.devfinder.service;

import com.jhops10.devfinder.domain.Developer;
import com.jhops10.devfinder.domain.Project;
import com.jhops10.devfinder.dto.DeveloperRequestDTO;
import com.jhops10.devfinder.dto.DeveloperResponseDTO;
import com.jhops10.devfinder.dto.ProjectDTO;
import com.jhops10.devfinder.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {

    @InjectMocks
    private DeveloperService developerService;

    @Mock
    private DeveloperRepository developerRepository;

    private Developer defaultDeveloper;

    @BeforeEach
    void setUp() {
        defaultDeveloper = createDeveloper("123", "Nome", true);
    }


    @Test
    void createDeveloper_shouldReturnDeveloper() {
        DeveloperRequestDTO requestDTO = DeveloperRequestDTO.builder()
                .name(defaultDeveloper.getName())
                .email(defaultDeveloper.getEmail())
                .location(defaultDeveloper.getLocation())
                .skills(defaultDeveloper.getSkills())
                .bio(defaultDeveloper.getBio())
                .available(defaultDeveloper.getAvailable())
                .projects(List.of(new ProjectDTO("Project1", "http://url.com", "Descrição")))
                .build();

        when(developerRepository.save(any(Developer.class))).thenReturn(defaultDeveloper);

        DeveloperResponseDTO sut = developerService.createDeveloper(requestDTO);

        assertNotNull(sut);
        assertEquals(defaultDeveloper.getId(), sut.getId());
        assertEquals(defaultDeveloper.getName(), sut.getName());
        assertEquals(defaultDeveloper.getEmail(), sut.getEmail());
        assertEquals(defaultDeveloper.getLocation(), sut.getLocation());
        assertEquals(defaultDeveloper.getSkills(), sut.getSkills());
        assertEquals(defaultDeveloper.getBio(), sut.getBio());
        assertEquals(defaultDeveloper.getAvailable(), sut.getAvailable());
        assertEquals(defaultDeveloper.getProjects().size(), sut.getProjects().size());

        verify(developerRepository).save(any(Developer.class));
    }


    @Test
    void getAvailableDevelopers_shouldReturnAvailableDevelopers() {
        when(developerRepository.findByAvailableTrue()).thenReturn(List.of(defaultDeveloper));

        List<DeveloperResponseDTO> sut = developerService.getAvailableDevelopers();

        assertEquals(1, sut.size());
        assertEquals("123", sut.get(0).getId());
        assertEquals("Nome", sut.get(0).getName());

        verify(developerRepository).findByAvailableTrue();
        verifyNoMoreInteractions(developerRepository);
    }

    @Test
    void getAvailableDevelopers_whenNoneAvailable_shouldReturnEmptyList() {
        when(developerRepository.findByAvailableTrue()).thenReturn(List.of());

        List<DeveloperResponseDTO> sut = developerService.getAvailableDevelopers();

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(developerRepository).findByAvailableTrue();
    }

    @Test
    void getDevelopersBySkill_shouldReturnMatchingDevelopersIgnoringCase() {
        when(developerRepository.findBySkillsContainingIgnoreCase("Skill1")).thenReturn(List.of(defaultDeveloper));

        List<DeveloperResponseDTO> sut = developerService.getDevelopersBySkill("Skill1");

        assertEquals(1, sut.size());
        assertEquals("123", sut.get(0).getId());
        assertEquals("Nome", sut.get(0).getName());
        assertEquals("Skill1", sut.get(0).getSkills().get(0));
        assertEquals("Skill2", sut.get(0).getSkills().get(1));

        verify(developerRepository).findBySkillsContainingIgnoreCase("Skill1");
    }

    @Test
    void getDevelopersBySkill_whenNoneAvailable_shouldReturnEmptyList() {
        when(developerRepository.findBySkillsContainingIgnoreCase("skill-inexistente")).thenReturn(List.of());

        List<DeveloperResponseDTO> sut = developerService.getDevelopersBySkill("skill-inexistente");

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(developerRepository).findBySkillsContainingIgnoreCase("skill-inexistente");
    }

    @Test
    void getDevelopersByLocation_shouldReturnMatchingDevelopersIgnoringCase() {
        when(developerRepository.findByLocationIgnoreCase("Location")).thenReturn(List.of(defaultDeveloper));

        List<DeveloperResponseDTO> sut = developerService.getDevelopersByLocation("Location");

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals("123", sut.get(0).getId());
        assertEquals("Nome", sut.get(0).getName());
        assertEquals("Skill1", sut.get(0).getSkills().get(0));
        assertEquals("Skill2", sut.get(0).getSkills().get(1));

        verify(developerRepository).findByLocationIgnoreCase("Location");
    }

    @Test
    void getDevelopersByLocation_whenNoneAvailable_shouldReturnEmptyList() {
        when(developerRepository.findByLocationIgnoreCase("local-inexistente")).thenReturn(List.of());

        List<DeveloperResponseDTO> sut = developerService.getDevelopersByLocation("local-inexistente");

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(developerRepository).findByLocationIgnoreCase("local-inexistente");
    }

    @Test
    void getAllDevelopers_shouldReturnAllDevelopers() {
        when(developerRepository.findAll()).thenReturn(List.of(defaultDeveloper));

        List<DeveloperResponseDTO> sut = developerService.getAll();

        assertNotNull(sut);
        assertEquals(1, sut.size());
        assertEquals("123", sut.get(0).getId());
        assertEquals("Nome", sut.get(0).getName());
        assertEquals("Skill1", sut.get(0).getSkills().get(0));
        assertEquals("Skill2", sut.get(0).getSkills().get(1));
    }

    @Test
    void getAllDevelopers_whenNoneAvailable_shouldReturnEmptyList() {
        when(developerRepository.findAll()).thenReturn(List.of());

        List<DeveloperResponseDTO> sut = developerService.getAll();

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(developerRepository).findAll();

    }

    private Developer createDeveloper(String id, String name, boolean available) {
        return Developer.builder()
                .id(id)
                .name(name)
                .email("email@dev.com")
                .location("Location")
                .skills(List.of("Skill1", "Skill2"))
                .bio("Exemplo Bio")
                .available(available)
                .projects(List.of(new Project("Project1", "http://url.com", "Descrição")))
                .createdAt(Instant.now())
                .build();
    }
}