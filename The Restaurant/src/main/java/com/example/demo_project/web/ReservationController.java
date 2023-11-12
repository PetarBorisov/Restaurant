package com.example.demo_project.web;

import com.example.demo_project.model.dto.ReservationAddDTO;
import com.example.demo_project.model.entity.ReservationEntity;
import com.example.demo_project.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReservationController {


    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/reservation")
    public ModelAndView showReservation(@ModelAttribute("reservationAddDTO") ReservationAddDTO reservationAddDTO) {
        return new ModelAndView("reservation");

    }

    @PostMapping("/reservation")
    public ModelAndView addReservation(@ModelAttribute("reservationAddDTO") @Valid ReservationAddDTO reservationAddDTO,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("reservation");
        }

        reservationService.addReservation(reservationAddDTO);

            return new ModelAndView("redirect:/successReservation");

        }
    @GetMapping("/successReservation")
    public ModelAndView successReservation(Model model) {
        return new ModelAndView("successReservation");
    }

    }
