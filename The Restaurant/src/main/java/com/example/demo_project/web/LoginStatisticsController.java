package com.example.demo_project.web;

import com.example.demo_project.util.LoginStatisticsInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class LoginStatisticsController {

    private final LoginStatisticsInterceptor loginStatisticsInterceptor;

    public LoginStatisticsController(LoginStatisticsInterceptor loginStatisticsInterceptor) {
        this.loginStatisticsInterceptor = loginStatisticsInterceptor;
    }
    @RequestMapping("/loginStatistic")
    public String loginStatistics(Model model, Principal principal) {
        if (isAdmin(principal)) {
            model.addAttribute("loginCounts", loginStatisticsInterceptor.getLoginCountMap());
            return "loginStatistic";
        } else {
            return "redirect:/";
        }
    }

    private boolean isAdmin(Principal principal) {
        if (principal != null) {
            return ((Authentication) principal).getAuthorities().stream()
                    .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        }
        return false;
    }

}
