package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.ReservationAddDTO;
import com.example.demo_project.model.entity.ReservationEntity;
import com.example.demo_project.repository.ReservationRepository;
import com.example.demo_project.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationServiceImplIntegrationTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;


    @Test
    void testAddReservation() {

        ReservationAddDTO reservationAddDTO = new ReservationAddDTO();
        reservationAddDTO.setId(1L);
        reservationAddDTO.setName("John Doe");
        reservationAddDTO.setEmail("john.doe@example.com");
        reservationAddDTO.setPhoneNumber("123456789");
        reservationAddDTO.setDate(LocalDate.parse("2023-01-01"));
        reservationAddDTO.setTime(LocalTime.parse("12:00"));
        reservationAddDTO.setNumberPersons(4);

        ReservationEntity existingEntity = new ReservationEntity();
        existingEntity.setId(1L);
        existingEntity.setName("Existing Reservation");

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(existingEntity));

        boolean result = reservationService.addReservation(reservationAddDTO);

        verify(reservationRepository, times(1)).save(existingEntity);
        assertEquals(false, result);
    }

}