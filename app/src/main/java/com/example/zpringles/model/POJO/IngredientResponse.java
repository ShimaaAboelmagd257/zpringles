package com.example.zpringles.model.POJO;

import java.util.List;

public class IngredientResponse {

    private List<Ingredient> meals;

    public List<Ingredient> getIngredients() {
        return meals;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.meals = ingredients;
    }
}
