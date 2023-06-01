package com.example.zpringles.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealModelResponse {
    @SerializedName("meals")
    private List<MealModel> meals;

    public MealModelResponse(List<MealModel> mealsModel) {
        this.meals = mealsModel;
    }

    public List<MealModel> getMealsModel() {
        return meals;
    }

    public void setMeals(List<MealModel> meals) {
        this.meals = meals;
    }
}
