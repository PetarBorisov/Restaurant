package com.example.demo_project.web;

import com.example.demo_project.model.entity.UserEntity;
import com.example.demo_project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/panel")
    public String showAdminPanel(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-panel";
    }

    @PostMapping("/promote")
    public String promoteToAdmin(@RequestParam Long userId) {
        // Логика за проверка на разрешенията
        // Променете ролята на потребителя с userId на администратор
        userService.promoteToAdmin(userId);
        return "redirect:/";

    }
    @PostMapping("/remove-admin")
    public String removeFromAdmin(@RequestParam Long adminId) {
        // Логика за проверка на разрешенията
        // Премахни администраторската роля от потребителя с userId
        userService.removeAdminRole(adminId);
        return "redirect:/admin/users/panel";
    }

}