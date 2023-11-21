package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.DrinkEditDto;
import com.example.demo_project.model.dto.DrinksAddDTO;
import com.example.demo_project.model.entity.DrinkEntity;
import com.example.demo_project.repository.DrinksRepository;
import com.example.demo_project.service.DrinksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class DrinksServiceImplTestIT {

    @Autowired
    private DrinksService drinksService;

    @MockBean
    private DrinksRepository drinksRepository;

    @Test
    void testAddDrinks() {

        DrinksAddDTO drinksAddDTO = new DrinksAddDTO();

        drinksAddDTO.setName("Test Drink");
        drinksAddDTO.setPhoto("test.jpg");
        drinksAddDTO.setDescription("Test Description");
        drinksAddDTO.setPrice(BigDecimal.valueOf(10.0));

        DrinkEntity existingDrink = new DrinkEntity();

        existingDrink.setId(1L);
        existingDrink.setName("Existing Drink");
        existingDrink.setPhoto("test.jpg");
        existingDrink.setDescription("Test Description");
        existingDrink.setPrice(BigDecimal.valueOf(10.0));

        when(drinksRepository.findByName("Test Drink")).thenReturn(Optional.of(existingDrink));

        drinksService.addDrinks(drinksAddDTO);

        verify(drinksRepository, times(1)).save(existingDrink);
    }

    @Test
    void testGetDrinkById() {

        Long drinkId = 1L;
        DrinkEntity drinkEntity = new DrinkEntity();
        drinkEntity.setId(drinkId);
        drinkEntity.setName("Test Drink");

        when(drinksRepository.findById(drinkId)).thenReturn(Optional.of(drinkEntity));

        DrinkEditDto result = drinksService.getDrinkById(drinkId);


        assertEquals(drinkId, result.getId());
        assertEquals("Test Drink", result.getName());
    }

    @Test
    void testGetAllDrinks() {

        DrinkEntity drinkEntity = new DrinkEntity();

        drinkEntity.setId(1L);
        drinkEntity.setName("Derby");
        drinkEntity.setPhoto("test.jpg");
        drinkEntity.setDescription("Test Description");
        drinkEntity.setPrice(BigDecimal.valueOf(10.0));

        when(drinksRepository.findAll()).thenReturn(Collections.singletonList(drinkEntity));

        List<DrinkEntity> result = drinksService.getAllDrinks();

        assertEquals(1, result.size());
        assertEquals("Derby", result.get(0).getName());
    }


    @Test
    void testGetDrinkEditDtoById() {

        Long drinkId = 1L;
        DrinkEntity drinkEntity = new DrinkEntity();
        drinkEntity.setId(drinkId);
        drinkEntity.setName("Test Drink");

        when(drinksRepository.findById(drinkId)).thenReturn(Optional.of(drinkEntity));

        DrinkEditDto result = drinksService.getDrinkEditDtoById(drinkId);

        assertEquals(drinkId, result.getId());
        assertEquals("Test Drink", result.getName());
    }
}

