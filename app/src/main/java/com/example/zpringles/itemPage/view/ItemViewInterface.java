package com.example.zpringles.itemPage.view;

import com.example.zpringles.model.MealModel;

import java.util.List;

public interface ItemViewInterface {

    void ViewMealItem(List<MealModel> meal);

    void addMealToPlan(MealModel Meal);

    public void addToFavorite (MealModel mealModel);
}
