package com.jhops10.devfinder.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "developers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer {

    @Id
    private String id;

    private String name;
    private String email;
    private String location;
    private List<String> skills;
    private String bio;

    private List<Project> projects;

    private Boolean available;
    private Instant createdAt;

}
