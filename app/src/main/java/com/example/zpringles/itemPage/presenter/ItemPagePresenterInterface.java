package com.example.zpringles.itemPage.presenter;

import com.example.zpringles.model.MealModel;

public interface ItemPagePresenterInterface {
    void getMealItem(String ItemName);

    public void addToFavorite(MealModel mealModel);

    public void addToPlan(MealModel mealModel);
}
