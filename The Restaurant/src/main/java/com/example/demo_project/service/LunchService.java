package com.example.demo_project.service;

import com.example.demo_project.model.dto.LunchAddDTO;
import com.example.demo_project.model.dto.LunchEditDTO;
import com.example.demo_project.model.entity.LunchEntity;

import java.util.List;

public interface LunchService {


    List<LunchEntity> getAllLunches();

    void addLunch(LunchAddDTO lunchAddDTO);

    LunchEditDTO getLunchById(Long id);

    LunchEditDTO getLunchEditDtoById(Long id);

    void saveLunch(LunchEditDTO lunchEditDTO);

    void editLunch(Long id, LunchEditDTO lunchEditDTO);

    void deleteLunch(Long id);

}
