package com.example.demo_project.config;

import com.example.demo_project.util.LoginStatisticsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    private final LoginStatisticsInterceptor loginStatisticsInterceptor;

    public WebMvcConfig(LoginStatisticsInterceptor loginStatisticsInterceptor) {
        this.loginStatisticsInterceptor = loginStatisticsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginStatisticsInterceptor);
    }
}