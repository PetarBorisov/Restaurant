package com.example.demo_project.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "dinner")
public class DinnerEntity extends BaseEntity{

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

    public DinnerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DinnerEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public DinnerEntity setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DinnerEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    public DinnerEntity setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }
}
