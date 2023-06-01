package com.example.zpringles.countries.presenter;

import com.example.zpringles.model.POJO.MealModel;

public interface CountriesPresenterInterface {
    void getMeals(String country);

    public void addToFavorite(MealModel mealModel);
}
