package com.burger.narucibruger.dto;

import java.util.List;

public class BurgerDto {
    private List<IngredientDto> ingredients;

    public BurgerDto() {
    }

    public BurgerDto(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
