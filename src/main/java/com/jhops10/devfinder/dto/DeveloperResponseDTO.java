package com.jhops10.devfinder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperResponseDTO {

    private String id;
    private String name;
    private String email;
    private String location;
    private List<String> skills;
    private String bio;
    private List<ProjectDTO> projects;
    private Boolean available;
    private Instant createdAt;
}
