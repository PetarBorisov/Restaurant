package com.example.demo_project.service;

import com.example.demo_project.model.dto.DrinkEditDto;
import com.example.demo_project.model.dto.DrinksAddDTO;
import com.example.demo_project.model.entity.DrinkEntity;

import java.util.List;

public interface DrinksService {


    void addDrinks(DrinksAddDTO drinksAddDTO);

   DrinkEditDto getDrinkById(Long id);

    List<DrinkEntity> getAllDrinks();


    void saveDrink(DrinkEditDto existingDrink);

    DrinkEditDto getDrinkEditDtoById(Long id);

}
