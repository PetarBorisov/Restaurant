package com.example.demo_project.service;

import com.example.demo_project.model.dto.DinnerAddDTO;
import com.example.demo_project.model.dto.DinnerEditDTO;
import com.example.demo_project.model.entity.DinnerEntity;

import java.util.List;

public interface DinnerService {


    List<DinnerEntity> getAllDinners();

    void addDinner(DinnerAddDTO dinnerAddDTO);

    DinnerEditDTO getDinnerById(Long id);

    DinnerEditDTO getDinnerEditDtoById(Long id);

    void saveDinner(DinnerEditDTO dinnerEditDTO);
}
