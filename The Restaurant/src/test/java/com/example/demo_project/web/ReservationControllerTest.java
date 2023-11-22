package com.example.demo_project.web;

import com.example.demo_project.model.dto.ReservationAddDTO;
import com.example.demo_project.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReservationWithValidInput() {
        ReservationAddDTO reservationAddDTO = new ReservationAddDTO();
        BindingResult bindingResult = mock(BindingResult.class);

        ModelAndView modelAndView = reservationController.addReservation(reservationAddDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("redirect:/successReservation", modelAndView.getViewName());


        verify(reservationService, times(1)).addReservation(reservationAddDTO);
    }

    @Test
    void testAddReservationWithInvalidInput() {
        ReservationAddDTO reservationAddDTO = new ReservationAddDTO();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = reservationController.addReservation(reservationAddDTO, bindingResult);

        assertNotNull(modelAndView);
        assertEquals("reservation", modelAndView.getViewName());


        verify(reservationService, never()).addReservation(any());

        assertFalse(modelAndView.getModel().containsKey("reservationAddDTO"));
    }

    @Test
    void testSuccessReservation() {
        ModelAndView modelAndView = reservationController.successReservation(null);

        assertNotNull(modelAndView);
        assertEquals("successReservation", modelAndView.getViewName());
    }
}