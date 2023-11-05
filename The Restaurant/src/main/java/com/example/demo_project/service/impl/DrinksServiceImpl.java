package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.DrinksAddDTO;
import com.example.demo_project.model.entity.DrinkEntity;
import com.example.demo_project.repository.DrinksRepository;
import com.example.demo_project.service.DrinksService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinksServiceImpl implements DrinksService {

    private final DrinksRepository drinksRepository;
    private final ModelMapper modelMapper;

    public DrinksServiceImpl(DrinksRepository drinksRepository, ModelMapper modelMapper) {
        this.drinksRepository = drinksRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addDrinks( DrinksAddDTO drinksAddDTO) {

        Optional<DrinkEntity> drink = this.drinksRepository.findByName(drinksAddDTO.getName());

        if (drink.isPresent()) {
            DrinkEntity existingDrink = drink.get();

            existingDrink.setName(drinksAddDTO.getName());
            existingDrink.setPhoto(drinksAddDTO.getPhoto());
            existingDrink.setDescription(drinksAddDTO.getDescription());
            existingDrink.setPrice(drinksAddDTO.getPrice());

            drinksRepository.save(existingDrink);

            DrinkEntity drinkEntity = this.modelMapper.map(drink, DrinkEntity.class);
            drinksRepository.save(drinkEntity);
        } else {
            // Create new drink!!!
            DrinkEntity newDrink = modelMapper.map(drinksAddDTO, DrinkEntity.class);
            drinksRepository.save(newDrink);

        }
    }

    @Override
    public DrinkEntity getDrinkById(Long drinkId) {
        Optional<DrinkEntity> drinkEntityOptional = drinksRepository.findById(drinkId);
        return drinkEntityOptional.orElse(null);

    }

    @Override
    public List<DrinkEntity> getAllDrinks() {
        return drinksRepository.findAll();
    }

    @Override
    public void editDrink(Long id, DrinkEntity drinkEntity) {
        Optional<DrinkEntity> existingDrinkOptional = drinksRepository.findById(id);
        if (existingDrinkOptional.isPresent()) {
            DrinkEntity existingDrink = existingDrinkOptional.get();
            existingDrink.setName(drinkEntity.getName());
            existingDrink.setPhoto(drinkEntity.getPhoto());
            existingDrink.setDescription(drinkEntity.getDescription());
            existingDrink.setPrice(drinkEntity.getPrice());

            drinksRepository.save(existingDrink); // Запазване на редактираната напитка в базата данни
        }
    }
}
