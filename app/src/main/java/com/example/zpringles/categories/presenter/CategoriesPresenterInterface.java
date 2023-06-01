package com.example.zpringles.categories.presenter;

import com.example.zpringles.model.MealModel;

public interface CategoriesPresenterInterface {
    void getMeals(String categories);

    public void addToFavorite(MealModel mealModel);

}
