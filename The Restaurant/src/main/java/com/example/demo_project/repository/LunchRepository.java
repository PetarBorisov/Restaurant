package com.example.demo_project.repository;

import com.example.demo_project.model.entity.LunchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LunchRepository extends JpaRepository<LunchEntity, Long> {

    Optional<LunchEntity> findByName(String name);

}
