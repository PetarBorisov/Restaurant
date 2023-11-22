package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.DinnerAddDTO;
import com.example.demo_project.model.dto.DinnerEditDTO;
import com.example.demo_project.model.entity.DinnerEntity;
import com.example.demo_project.repository.DinnerRepository;
import com.example.demo_project.service.DinnerService;
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
class DinnerServiceImplTest {

    @MockBean
    private DinnerRepository dinnerRepository;

    @Autowired
    private DinnerService dinnerService;

    @Test
    void testAddDinner() {

        DinnerAddDTO dinnerAddDTO = new DinnerAddDTO();

        dinnerAddDTO.setName("Test Dinner");
        dinnerAddDTO.setPhoto("testDinner.jpg");
        dinnerAddDTO.setDescription("Test Dinner Description");
        dinnerAddDTO.setPrice(BigDecimal.valueOf(11.0));

        DinnerEntity existingDinner = new DinnerEntity();

        existingDinner.setId(1L);
        existingDinner.setName("Existing Dinner");
        existingDinner.setPhoto("dinner.jpg");
        existingDinner.setDescription("Test Dinner Descr.");
        existingDinner.setPrice(BigDecimal.valueOf(11.0));

        when(dinnerRepository.findByName("Test Dinner")).thenReturn(Optional.of(existingDinner));

        dinnerService.addDinner(dinnerAddDTO);

        verify(dinnerRepository, times(1)).save(existingDinner);
    }

    @Test
    void testGetAllDinner() {

        DinnerEntity dinnerEntity = new DinnerEntity();

        dinnerEntity.setId(1L);
        dinnerEntity.setName("BurgerKing");
        dinnerEntity.setPhoto("test.jpg");
        dinnerEntity.setDescription("Test Dinner Description");
        dinnerEntity.setPrice(BigDecimal.valueOf(10.0));

        when(dinnerRepository.findAll()).thenReturn(Collections.singletonList(dinnerEntity));

        List<DinnerEntity> result = dinnerService.getAllDinners();

        assertEquals(1, result.size());
        assertEquals("BurgerKing", result.get(0).getName());
    }


    @Test
    void testGetDinnerEditDtoById() {

        Long dinnerId = 1L;
        DinnerEntity dinnerEntity = new DinnerEntity();
        dinnerEntity.setId(dinnerId);
        dinnerEntity.setName("Test Dinner");

        when(dinnerRepository.findById(dinnerId)).thenReturn(Optional.of(dinnerEntity));

        DinnerEditDTO result = dinnerService.getDinnerEditDtoById(dinnerId);

        assertEquals(dinnerId, result.getId());
        assertEquals("Test Dinner", result.getName());
    }
}
