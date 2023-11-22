package com.example.demo_project.service.impl;




import com.example.demo_project.model.dto.LunchAddDTO;
import com.example.demo_project.model.dto.LunchEditDTO;
import com.example.demo_project.model.entity.LunchEntity;
import com.example.demo_project.repository.LunchRepository;
import com.example.demo_project.service.LunchService;
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
import static org.mockito.Mockito.when;

@SpringBootTest
class LunchServiceImplTestIT {

    @Autowired
    private LunchService lunchService;

    @MockBean
    private LunchRepository lunchRepository;

    @Test
    void testAddLunch() {

        LunchAddDTO lunchAddDTO = new LunchAddDTO();

        lunchAddDTO.setName("Test Lunch");
        lunchAddDTO.setPhoto("testLunch.jpg");
        lunchAddDTO.setDescription("Test Description");
        lunchAddDTO.setPrice(BigDecimal.valueOf(10.0));

        LunchEntity existingLunch = new LunchEntity();

        existingLunch.setId(1L);
        existingLunch.setName("Existing Drink");
        existingLunch.setPhoto("testLunch.jpg");
        existingLunch.setDescription("Test Description");
        existingLunch.setPrice(BigDecimal.valueOf(10.0));

        when(lunchRepository.findByName("Test Lunch")).thenReturn(Optional.of(existingLunch));

        lunchService.addLunch(lunchAddDTO);

        verify(lunchRepository, times(1)).save(existingLunch);
    }

    @Test
    void testGetLunchById() {

        Long lunchId = 1L;
        LunchEntity lunchEntity = new LunchEntity();

        lunchEntity.setId(lunchId);
        lunchEntity.setName("Test Lunch");

        when(lunchRepository.findById(lunchId)).thenReturn(Optional.of(lunchEntity));

        LunchEditDTO result = lunchService.getLunchById(lunchId);


        assertEquals(lunchId, result.getId());
        assertEquals("Test Lunch", result.getName());
    }

    @Test
    void testGetAllLunches() {

        LunchEntity lunchEntity = new LunchEntity();

        lunchEntity.setId(1L);
        lunchEntity.setName("Steak");
        lunchEntity.setPhoto("test.jpg");
        lunchEntity.setDescription("Test Lunch Description");
        lunchEntity.setPrice(BigDecimal.valueOf(10.0));

        when(lunchRepository.findAll()).thenReturn(Collections.singletonList(lunchEntity));

        List<LunchEntity> result = lunchService.getAllLunches();

        assertEquals(1, result.size());
        assertEquals("Steak", result.get(0).getName());
    }


    @Test
    void testGetLunchEditDtoById() {

        Long lunchId = 1L;
        LunchEntity lunchEntity = new LunchEntity();
        lunchEntity.setId(lunchId);
        lunchEntity.setName("Test Lunch");

        when(lunchRepository.findById(lunchId)).thenReturn(Optional.of(lunchEntity));

        LunchEditDTO result = lunchService.getLunchEditDtoById(lunchId);

        assertEquals(lunchId, result.getId());
        assertEquals("Test Lunch", result.getName());
    }
}

