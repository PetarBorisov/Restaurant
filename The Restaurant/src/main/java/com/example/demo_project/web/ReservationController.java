package com.example.demo_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservationController {

    @GetMapping("/reservation")
    public ModelAndView reservation() {
        return new ModelAndView("reservation");
    }

}
