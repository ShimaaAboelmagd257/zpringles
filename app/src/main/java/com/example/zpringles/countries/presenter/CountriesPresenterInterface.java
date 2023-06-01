package com.example.zpringles.countries.presenter;

import com.example.zpringles.model.MealModel;

public interface CountriesPresenterInterface {
    void getMeals(String country);

    public void addToFavorite(MealModel mealModel);
}
