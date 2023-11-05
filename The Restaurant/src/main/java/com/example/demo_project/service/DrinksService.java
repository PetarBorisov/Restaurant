package com.example.demo_project.service;

import com.example.demo_project.model.dto.DrinksAddDTO;
import com.example.demo_project.model.entity.DrinkEntity;

import java.util.List;

public interface DrinksService {

    void addDrinks(DrinksAddDTO drinksAddDTO);

   DrinkEntity getDrinkById(Long drinkId);

    List<DrinkEntity> getAllDrinks();

    void editDrink(Long id, DrinkEntity drinkEntity);

}
