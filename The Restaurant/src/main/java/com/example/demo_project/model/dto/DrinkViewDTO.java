package com.example.demo_project.model.dto;

import java.util.ArrayList;
import java.util.List;

public class DrinkViewDTO {
    
    private List<DrinkEditDto> getDrink;

    public DrinkViewDTO() {
        this(new ArrayList<>());
    }

    public DrinkViewDTO(List<DrinkEditDto> getDrink) {
        this.getDrink = getDrink;
    }

    public List<DrinkEditDto> getGetDrink() {
        return getDrink;
    }
}
