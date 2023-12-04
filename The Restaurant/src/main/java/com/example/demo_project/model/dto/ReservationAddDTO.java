package com.example.demo_project.model.dto;


import jakarta.validation.constraints.*;


import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationAddDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @Email
    @NotBlank
    private String email;


    @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15 characters!")
    private String phoneNumber;


    private Integer numberPersons;


    private Integer tables;

    @NotNull
    @FutureOrPresent(message = "Date must be on present or in the future!")
    private LocalDate date;

    @NotNull(message ="Please enter the time!")
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
