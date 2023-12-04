package com.example.demo_project.model.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends BaseEntity {

    @OneToMany(mappedBy = "menu")
    private List<DrinkEntity> drinkEntities;

    @OneToMany(mappedBy = "menu")
    private List<LunchEntity> lunchEntities;

    @OneToMany(mappedBy = "menu")
    private List<DinnerEntity> dinnerEntities;

    public List<DrinkEntity> getDrinkEntities() {
        return drinkEntities;
    }

    public void setDrinkEntities(List<DrinkEntity> drinkEntities) {
        this.drinkEntities = drinkEntities;
    }

    public List<LunchEntity> getLunchEntities() {
        return lunchEntities;
    }

    public void setLunchEntities(List<LunchEntity> lunchEntities) {
        this.lunchEntities = lunchEntities;
    }

    public List<DinnerEntity> getDinnerEntities() {
        return dinnerEntities;
    }

    public void setDinnerEntities(List<DinnerEntity> dinnerEntities) {
        this.dinnerEntities = dinnerEntities;
    }
}