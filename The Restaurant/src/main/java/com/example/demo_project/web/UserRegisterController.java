package com.example.demo_project.web;

import com.example.demo_project.model.dto.UserRegisterDTO;
import com.example.demo_project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/users")
@Controller
public class UserRegisterController {

   private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String register(@ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO) {
        return "auth-register";
    }
    @PostMapping("/register")
    public String  register(@ModelAttribute("userRegisterDTO") @Valid UserRegisterDTO userRegisterDTO,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "auth-register";
        }

        boolean hasSuccessFullRegistration = userService.registerUser(userRegisterDTO);

        if (!hasSuccessFullRegistration) {
            redirectAttributes.addFlashAttribute("hasRegisterError", true);
            return "redirect:/users/register";

        }
        redirectAttributes.addFlashAttribute("successMessage", "Регистрацията беше успешна. Моля, влезте в профила си.");
        return "redirect:/login";

        //TODO: Registration email with activation link;
    }
}
