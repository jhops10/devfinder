package com.jhops10.devfinder.repository;

import com.jhops10.devfinder.domain.Developer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends MongoRepository<Developer, String> {

    List<Developer> findBySkillsContainingIgnoreCase(String skill);

    List<Developer> findByAvailableTrue();

    List<Developer> findByLocationIgnoreCase(String location);
}
