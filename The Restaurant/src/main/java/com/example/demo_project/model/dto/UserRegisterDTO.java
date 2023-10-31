package com.example.demo_project.model.dto;

import com.example.demo_project.model.validation.FieldMatch;
import com.example.demo_project.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match.")
public class UserRegisterDTO {


    @NotBlank
    @Size(min = 2, max = 20, message = "First name length must be between 3 and 20 characters!")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message = "Last name length must be between 3 and 20 characters!")
    private String lastName;


    @Email
    @UniqueUserEmail
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @NotBlank
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
