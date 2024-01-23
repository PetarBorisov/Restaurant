package com.example.demo_project.web;

import com.example.demo_project.model.dto.DinnerAddDTO;
import com.example.demo_project.model.dto.DinnerEditDTO;
import com.example.demo_project.model.dto.LunchAddDTO;
import com.example.demo_project.model.dto.LunchEditDTO;
import com.example.demo_project.model.entity.DinnerEntity;
import com.example.demo_project.model.entity.LunchEntity;
import com.example.demo_project.service.DinnerService;
import com.example.demo_project.service.LunchService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FoodController {

    private final LunchService lunchService;
    private final DinnerService dinnerService;

    public FoodController(LunchService lunchService, DinnerService dinnerService) {
        this.lunchService = lunchService;
        this.dinnerService = dinnerService;
    }

    @GetMapping("/lunches")
    public ModelAndView showLunch(Model model) {
        List<LunchEntity> myLunches = lunchService.getAllLunches();
        model.addAttribute("myLunches", myLunches);
        return new ModelAndView("lunches");
    }

    @GetMapping("/add/lunch")
    public ModelAndView addLunch(@ModelAttribute("lunchAddDTO") LunchAddDTO lunchAddDTO) {
        return new ModelAndView("add_lunches");
    }

    @PostMapping("/add/lunch")
    public ModelAndView addLunch(@ModelAttribute("lunchAddDTO") @Valid LunchAddDTO lunchAddDTO,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("add_lunches");
        }
        lunchService.addLunch(lunchAddDTO);
        return new ModelAndView("redirect:/lunches");
    }

    @GetMapping("/edit/lunch/{id}")
    public ModelAndView showEditLunchForm(@PathVariable("id") Long id, Model model) {
        LunchEditDTO lunch = lunchService.getLunchById(id);
        model.addAttribute("lunch", lunch);
        return new ModelAndView("edit_lunch");
    }

    @PostMapping("/edit/lunch/{id}")
    public ModelAndView editLunch(@PathVariable("id") Long id, @ModelAttribute("lunch") @Valid LunchEditDTO lunchEditDTO,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/edit_lunch");
        }
        LunchEditDTO existingLunch = lunchService.getLunchEditDtoById(id);

        if (existingLunch == null) {
            return new ModelAndView("redirect:edit_lunch");
        }
        lunchService.editLunch(id, lunchEditDTO);
        return new ModelAndView("redirect:/lunches");
    }
    @PostMapping("/delete/lunch/{id}")
     public ModelAndView removeLunch(@PathVariable("id") Long id) {
        lunchService.deleteLunch(id);
        return new ModelAndView("redirect:/lunches");
    }

    @GetMapping("/dinners")
    public ModelAndView showDinner(Model model) {
        List<DinnerEntity> myDinners = dinnerService.getAllDinners();
        model.addAttribute("myDinners", myDinners);
        return new ModelAndView("dinners");
    }

    @GetMapping("/add/dinner")
    public ModelAndView addDinner(@ModelAttribute("dinnerAddDTO") DinnerAddDTO dinnerAddDTO) {
        return new ModelAndView("add_dinners");
    }

    @PostMapping("/add/dinner")
    public ModelAndView addDinner(@ModelAttribute("dinnerAddDTO") @Valid DinnerAddDTO dinnerAddDTO,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("add_dinners");
        }
        dinnerService.addDinner(dinnerAddDTO);
        return new ModelAndView("redirect:/dinners");
    }

    @GetMapping("/edit/dinner/{id}")
    public ModelAndView showEditDinnerForm(@PathVariable("id") Long id, Model model) {
        DinnerEditDTO dinner = dinnerService.getDinnerById(id);
        model.addAttribute("dinner", dinner);
        return new ModelAndView("edit_dinner");
    }

    @PostMapping("/edit/dinner/{id}")
    public ModelAndView editDinner(@PathVariable("id") Long id, @ModelAttribute("dinner") @Valid DinnerEditDTO dinnerEditDTO,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/edit_dinner");
        }
        DinnerEditDTO existingDinner = dinnerService.getDinnerEditDtoById(id);

        if (existingDinner == null) {
            return new ModelAndView("redirect:/edit_dinner");
        }

           dinnerService.editDinner(id, dinnerEditDTO);
            return new ModelAndView("redirect:/dinners");

        }
        @PostMapping("/delete/dinner/{id}")
        public ModelAndView removeDinner(@PathVariable("id") Long id) {
        dinnerService.deleteDinner(id);
        return new ModelAndView("redirect:/dinners");
        }

    }