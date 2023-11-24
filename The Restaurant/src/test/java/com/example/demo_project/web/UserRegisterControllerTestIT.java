package com.example.demo_project.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterControllerTestIT {


    @Autowired
    private MockMvc mockMvc;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    private GreenMail greenMail;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(port, host,"smtp"));
        greenMail.start();
        greenMail.setUser(username, password);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/users/register")

                                .param("email", "pesho@softuni.com")
                                .param("firstName", "Pesho")
                                .param("lastName", "Petrov")
                                .param("password", "topsecret")
                                .param("confirmPassword", "topsecret")
                                .with(csrf())
                ).andExpect(view().name("/login"));


    }

    @Test
    void testRegisterPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"));
    }
    @Test
    void testUnsuccessfulRegistration() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/users/register")
                                .param("email", "example.com")
                                .param("firstName", "P")
                                .param("lastName", "P")
                                .param("password", "11")
                                .param("confirmPassword", "11")
                                .with(csrf())
                ).andExpect(status().isOk())
                .andExpect(view().name("auth-register"))
                .andExpect(model().attributeHasFieldErrors("userRegisterDTO", "firstName","lastName","email","password"));;

    }

}