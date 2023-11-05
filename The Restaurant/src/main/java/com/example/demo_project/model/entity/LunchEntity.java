package com.example.demo_project.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "lunches")
public class LunchEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String photo;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Menu menu;


    public String getName() {
        return name;
    }

    public LunchEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public LunchEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public LunchEntity setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LunchEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    public LunchEntity setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }
}
