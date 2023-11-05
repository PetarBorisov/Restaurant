package com.example.demo_project.repository;


import com.example.demo_project.model.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinksRepository extends JpaRepository<DrinkEntity, Long> {


    Optional<DrinkEntity> findById(Long id);

    Optional<DrinkEntity> findByName(String name);

}
