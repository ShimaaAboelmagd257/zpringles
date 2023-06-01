package com.example.zpringles.countries.view;

import com.example.zpringles.model.POJO.MealModel;

import java.util.List;

public interface CountriesViewInterface {
    void ViewCounteryMeal(List<MealModel> models);
    public void addToFavorite (MealModel mealModel);

}
