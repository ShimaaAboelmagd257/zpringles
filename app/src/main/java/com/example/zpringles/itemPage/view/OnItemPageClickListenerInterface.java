package com.example.zpringles.itemPage.view;

import com.example.zpringles.model.POJO.MealModel;

public interface OnItemPageClickListenerInterface {

    void addToFavoriteOnClick(MealModel mealModel);

    void addToWeakPlanOnclick(MealModel mealModel);

}
