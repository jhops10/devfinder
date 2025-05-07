package com.jhops10.devfinder.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeveloperRequestDTO {

    @NotBlank(message = "O campo nome é obrigatório.")
    private String name;

    @NotBlank(message = "O campo email é obrigatório.")
    @Email
    private String email;

    @NotBlank(message = "O campo localização é obrigatório.")
    private String location;

    @NotEmpty(message = "Você deve adicionar pelo menos uma skill.")
    private List<String> skills;

    private String bio;
    private List<ProjectDTO> projects;
    private Boolean available;
}
