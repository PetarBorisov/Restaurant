package com.example.demo_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public ModelAndView menu(){
        return new ModelAndView("menu");
    }

    @GetMapping("/drinks")
    public ModelAndView drinks(){
        return new ModelAndView(("drinks"));
    }
}
