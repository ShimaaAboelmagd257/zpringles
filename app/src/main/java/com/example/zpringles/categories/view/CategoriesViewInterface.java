package com.example.zpringles.categories.view;

import com.example.zpringles.model.POJO.MealModel;

import java.util.List;

public interface CategoriesViewInterface {

    void ViewCategoriesMeal(List<MealModel> models);
    public void addToFavorite (MealModel mealModel);
}
