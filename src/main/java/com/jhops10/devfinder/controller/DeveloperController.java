package com.jhops10.devfinder.controller;

import com.jhops10.devfinder.dto.DeveloperRequestDTO;
import com.jhops10.devfinder.dto.DeveloperResponseDTO;
import com.jhops10.devfinder.service.DeveloperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping
    public ResponseEntity<DeveloperResponseDTO> createDeveloper(@RequestBody @Valid DeveloperRequestDTO dto) {
        DeveloperResponseDTO response = developerService.createDeveloper(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DeveloperResponseDTO>> getAll() {
        return ResponseEntity.ok(developerService.getAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<DeveloperResponseDTO>> getAvailableDevelopers() {
        return ResponseEntity.ok(developerService.getAvailableDevelopers());
    }

    @GetMapping("/skill")
    public ResponseEntity<List<DeveloperResponseDTO>> getDevelopersBySkill(@RequestParam String skill) {
        return ResponseEntity.ok(developerService.getDevelopersBySkill(skill));
    }

    @GetMapping("/location")
    public ResponseEntity<List<DeveloperResponseDTO>> getDevelopersByLocation(@RequestParam String location) {
        return ResponseEntity.ok(developerService.getDevelopersByLocation(location));
    }
}
