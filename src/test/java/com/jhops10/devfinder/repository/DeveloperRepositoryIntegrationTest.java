package com.jhops10.devfinder.repository;

import com.jhops10.devfinder.domain.Developer;
import com.jhops10.devfinder.domain.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class DeveloperRepositoryIntegrationTest {

    @Autowired
    private DeveloperRepository developerRepository;

    @BeforeEach
    void setUp() {
        developerRepository.deleteAll();

        Developer dev = Developer.builder()
                .name("João")
                .email("joao@email.com")
                .location("São Paulo")
                .skills(List.of("Java", "Spring"))
                .bio("Backend Developer")
                .available(true)
                .projects(List.of(new Project("Project1", "http://url.com", "Descrição")))
                .createdAt(Instant.now())
                .build();

        developerRepository.save(dev);
    }

    @Test
    void findByAvailableTrue_shouldReturnAvailableDevelopers() {
        List<Developer> result = developerRepository.findByAvailableTrue();

        assertEquals(1, result.size());
        assertEquals("João",result.get(0).getName());
    }

    @Test
    void findByLocationIgnoreCase_shouldReturnMatchingDevelopers() {
        List<Developer> result = developerRepository.findByLocationIgnoreCase("são paulo");

        assertEquals(1, result.size());
        assertEquals("joao@email.com",result.get(0).getEmail());
    }
}
