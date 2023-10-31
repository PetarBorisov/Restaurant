package com.example.demo_project.web;

import com.example.demo_project.model.entity.UserEntity;
import com.example.demo_project.model.enums.UserRoleEnum;
import com.example.demo_project.repository.UserRepository;
import com.example.demo_project.repository.UserRoleRepository;
import com.example.demo_project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserService userService;

    public HomeController(UserRepository userRepository, UserRoleRepository userRoleRepository, UserService userService) {
        this.userRepository = userRepository;

        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
    }


    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/about")
    public String about() {
        return "about";

    }

    @GetMapping("/contact")
    public ModelAndView contacts() {

        return new ModelAndView("contact");
    }

    @GetMapping("/stuff")
    public ModelAndView stuff() {
        return new ModelAndView("stuff");
    }

    @GetMapping("/gallery")
    public ModelAndView gallery() {
        return new ModelAndView("gallery");
    }


}