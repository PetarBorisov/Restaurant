package com.example.demo_project.web;

import com.example.demo_project.model.dto.DrinkEditDto;
import com.example.demo_project.model.dto.LunchAddDTO;
import com.example.demo_project.model.dto.LunchEditDTO;
import com.example.demo_project.model.entity.LunchEntity;
import com.example.demo_project.service.LunchService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FoodController {

    private final LunchService lunchService;

    public FoodController(LunchService lunchService) {
        this.lunchService = lunchService;
    }

    @GetMapping("/lunches")
    public ModelAndView showLunch(Model model){
        List<LunchEntity> myLunches = lunchService.getAllLunches();
        model.addAttribute("myLunches", myLunches);
        return new ModelAndView("lunches");
    }
    @GetMapping("/add/lunch")
    public ModelAndView addLunch(@ModelAttribute("lunchAddDTO")LunchAddDTO lunchAddDTO) {
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
    public ModelAndView showEditDrinkForm(@PathVariable("id") Long id, Model model) {
        LunchEditDTO lunch = lunchService.getLunchById(id);
        model.addAttribute("lunch", lunch);
        return new ModelAndView("edit_lunch");
    }

    @PostMapping("/edit/lunch/{id}")
    public ModelAndView editLunch(@PathVariable("id") Long id,@ModelAttribute("lunch") @Valid LunchEditDTO lunchEditDTO,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ModelAndView("redirect:/edit_lunch");
        }
        LunchEditDTO existingLunch = lunchService.getLunchEditDtoById(id);

        if (existingLunch == null) {
            return new ModelAndView("redirect:edit_lunch");
        }
        existingLunch.setName(lunchEditDTO.getName());
        existingLunch.setPhoto(lunchEditDTO.getPhoto());
        existingLunch.setDescription(lunchEditDTO.getDescription());
        existingLunch.setPrice(lunchEditDTO.getPrice());

        lunchService.saveLunch(existingLunch);


        return new ModelAndView("redirect:/lunches");
    }

    @GetMapping("/dinners")
    public ModelAndView dinner(){
        return new ModelAndView("dinners");
    }

}
