package com.example.demo_project.web;

import com.example.demo_project.model.dto.DrinksAddDTO;
import com.example.demo_project.model.entity.DrinkEntity;
import com.example.demo_project.service.DrinksService;
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
public class MenuAndDrinkController {

    private final DrinksService drinksService;

    public MenuAndDrinkController(DrinksService drinksService) {
        this.drinksService = drinksService;
    }

    @GetMapping("/menu")
    public ModelAndView menu() {
        return new ModelAndView("menu");
    }

    @GetMapping("/drinks")
    public ModelAndView drinks(Model model) {
        List<DrinkEntity> drinks = drinksService.getAllDrinks();
        model.addAttribute("drinks", drinks);
        return new ModelAndView("drinks", "modelKey", drinks);
    }

    @GetMapping("/add/drink")
    public ModelAndView addDrink(@ModelAttribute("drinksAddDTO") DrinksAddDTO drinksAddDTO) {
        return new ModelAndView("edit_drinks");
    }

    @PostMapping("/add/drink")
    public ModelAndView addDrink(@ModelAttribute("drinksAddDTO") @Valid DrinksAddDTO drinksAddDTO,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("edit_drinks");
        }

        drinksService.addDrinks(drinksAddDTO);
        return new ModelAndView("redirect:/drinks");
    }


    @GetMapping("/edit/drink/{id}")
    public ModelAndView showEditDrinkForm(@PathVariable Long id, Model model) {
        DrinkEntity drink = drinksService.getDrinkById(id);
        model.addAttribute("drink", drink);
        return new ModelAndView("edit_drinks", "drink", drink);
    }

    @PostMapping("/edit/drink/{id}")
    public ModelAndView editDrink(@PathVariable Long id, @ModelAttribute("drink") @Valid DrinksAddDTO drinksAddDTO,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("edit_drinks");
        }
        DrinkEntity drinkEntity = convertDtoToEntity(drinksAddDTO);
        // Вашата логика за редакция на напитката с ID
        drinksService.editDrink(id, drinkEntity);
        return new ModelAndView("redirect:/drinks");
    }

    private DrinkEntity convertDtoToEntity(DrinksAddDTO drinksAddDTO) {
        DrinkEntity drinkEntity = new DrinkEntity();
        drinkEntity.setId(drinksAddDTO.getId());
        drinkEntity.setName(drinksAddDTO.getName());
        drinkEntity.setPhoto(drinksAddDTO.getPhoto());
        drinkEntity.setDescription(drinksAddDTO.getDescription());
        drinkEntity.setPrice(drinksAddDTO.getPrice());
        return drinkEntity;
    }
}




