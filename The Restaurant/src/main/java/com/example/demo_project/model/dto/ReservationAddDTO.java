package com.example.demo_project.model.dto;

import com.example.demo_project.model.validation.UniqueUserEmail;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationAddDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 3, max = 15, message = "Phone number length must be between 10 and 15 characters!")
    private Integer phoneNumber;

    @NotBlank
    private Integer numberPersons;


    private Integer tables;

    @FutureOrPresent(message = "Date must be on present or in the future!")
    private LocalDate date;

    @Future(message = "Time must be in the future!")
    private LocalTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberPersons() {
        return numberPersons;
    }

    public void setNumberPersons(Integer numberPersons) {
        this.numberPersons = numberPersons;
    }

    public Integer getTables() {
        return tables;
    }

    public void setTables(Integer tables) {
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
