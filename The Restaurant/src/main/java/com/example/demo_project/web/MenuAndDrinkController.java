package com.example.demo_project.web;

import com.example.demo_project.model.dto.DrinkEditDto;
import com.example.demo_project.model.dto.DrinksAddDTO;
import com.example.demo_project.model.entity.DrinkEntity;
import com.example.demo_project.repository.DrinksRepository;
import com.example.demo_project.service.DrinksService;
import com.example.demo_project.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MenuAndDrinkController {

    private final DrinksService drinksService;
    private final DrinksRepository drinksRepository;

    public MenuAndDrinkController(DrinksService drinksService, DrinksRepository drinksRepository) {
        this.drinksService = drinksService;
        this.drinksRepository = drinksRepository;
    }

    @GetMapping("/menu")
    public ModelAndView menu() {
        return new ModelAndView("menu");
    }

    @GetMapping("/drinks")
    public ModelAndView showDrinks(Model model) {
        List<DrinkEntity> myDrinks = drinksService.getAllDrinks();
        model.addAttribute("myDrinks", myDrinks);
        return new ModelAndView("drinks");
    }

    @GetMapping("/add/drink")
    public ModelAndView addDrink(@ModelAttribute("drinksAddDTO") DrinksAddDTO drinksAddDTO) {
        return new ModelAndView("add_drinks");
    }

    @PostMapping("/add/drink")
    public ModelAndView addDrink(@ModelAttribute("drinksAddDTO") @Valid DrinksAddDTO drinksAddDTO,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("add_drinks");
        }

        drinksService.addDrinks(drinksAddDTO);
        return new ModelAndView("redirect:/drinks");
    }


    @GetMapping("/edit/drink/{id}")
    public ModelAndView showEditDrinkForm(@PathVariable("id") Long id, Model model) {
        DrinkEditDto drink = drinksService.getDrinkById(id);
        model.addAttribute("drink", drink);
        return new ModelAndView("edit_drink");


    }
    @PostMapping("/edit/drink/{id}")
    public ModelAndView editDrink(@PathVariable("id") Long id, @ModelAttribute("drink") @Valid DrinkEditDto drinkEditDto,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redirect:/edit_drink");
        }

        DrinkEditDto existingDrink = drinksService.getDrinkEditDtoById(id);

        if (existingDrink == null) {
            return new ModelAndView("redirect:edit_drink");
        }

         drinksService.editDrink(id, drinkEditDto);
        return new ModelAndView("redirect:/drinks");
    }
@PostMapping("/delete/drink/{id}")
    public ModelAndView removeDrink(@PathVariable("id") Long id) {
    drinksService.deleteDrink(id);
    return new ModelAndView("redirect:/drinks");
}

}




