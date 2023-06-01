package com.example.zpringles.planmeals.view;

import com.example.zpringles.model.POJO.MealModel;

public interface OnPlanClickListner {
    void onRemovePlanClicked(MealModel mealModel);

    void addToFavoriteOnClick(MealModel mealModel);

}
