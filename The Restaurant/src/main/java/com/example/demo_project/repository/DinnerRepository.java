package com.example.demo_project.repository;

import com.example.demo_project.model.entity.DinnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DinnerRepository extends JpaRepository<DinnerEntity, Long> {

    Optional<DinnerEntity> findByName(String name);

}
