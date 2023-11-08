package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.DrinkEditDto;
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
    public DrinkEditDto getDrinkById(Long id) {
        Optional<DrinkEntity> drinkEntityOptional = drinksRepository.findById(id);
        if (drinkEntityOptional.isPresent()) {
            DrinkEntity drinkEntity = drinkEntityOptional.get();
            return convertEntityToEditDto(drinkEntity);
        }
        return null;
    }

    private DrinkEditDto convertEntityToEditDto(DrinkEntity drinkEntity) {
        DrinkEditDto editDto = new DrinkEditDto();
        editDto.setId(drinkEntity.getId());
        editDto.setName(drinkEntity.getName());
        editDto.setPhoto(drinkEntity.getPhoto());
        editDto.setDescription(drinkEntity.getDescription());
        editDto.setPrice(drinkEntity.getPrice());
        return editDto;
    }


    @Override
    public List<DrinkEntity> getAllDrinks() {
        return drinksRepository.findAll();
    }


        @Override
        public void saveDrink(DrinkEditDto drinkEditDto) {

            DrinkEntity drinkEntity = modelMapper.map(drinkEditDto, DrinkEntity.class);

            drinksRepository.save(drinkEntity);
    }

    @Override
    public DrinkEditDto getDrinkEditDtoById(Long id) {
        Optional<DrinkEntity> drinkEntityOptional = drinksRepository.findById(id);
        return drinkEntityOptional.map(drinkEntity -> modelMapper.map(drinkEntity, DrinkEditDto.class)).orElse(null);

    }

}
