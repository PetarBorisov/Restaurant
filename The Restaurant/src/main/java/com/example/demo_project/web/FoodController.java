package com.example.demo_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FoodController {

    @GetMapping("/lunches")
    public ModelAndView lunch(){
        return new ModelAndView("lunches");
    }


    @GetMapping("/dinners")
    public ModelAndView dinner(){
        return new ModelAndView("dinners");
    }

}
