package com.example.demo_project.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import java.math.BigDecimal;

@Entity
@Table(name = "drinks")
public class DrinkEntity extends BaseEntity {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String photo;

    private BigDecimal price;

    @ManyToOne
    private Menu menu;


    public String getName() {
        return name;
    }

    public DrinkEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DrinkEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DrinkEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public DrinkEntity setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    public DrinkEntity setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }
}
