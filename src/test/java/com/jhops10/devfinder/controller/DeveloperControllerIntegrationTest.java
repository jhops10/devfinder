package com.jhops10.devfinder.controller;

import com.jhops10.devfinder.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeveloperControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeveloperRepository developerRepository;

    @BeforeEach
    void setup() {
        developerRepository.deleteAll();
    }

    @Test
    void shouldCreateDeveloper() throws Exception {
        String requestBody = """
        {
          "name": "João",
          "email": "joao@email.com",
          "location": "São Paulo",
          "skills": ["Java", "Spring Boot"],
          "bio": "Dev backend",
          "available": true,
          "projects": [
            {
              "name": "Projeto API",
              "url": "http://github.com",
              "description": "Projeto portfólio"
            }
          ]
        }
        """;

        mockMvc.perform(post("/api/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("João"));
    }
}
