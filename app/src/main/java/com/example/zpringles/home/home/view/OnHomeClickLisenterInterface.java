package com.example.zpringles.home.home.view;

import com.example.zpringles.model.POJO.MealModel;

public interface OnHomeClickLisenterInterface {
    void addToFavoriteOnClick(MealModel mealModel);
    void nevToItemPage(MealModel model);

}
