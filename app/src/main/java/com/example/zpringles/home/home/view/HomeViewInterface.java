package com.example.zpringles.home.home.view;


import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;

import java.util.List;

public interface HomeViewInterface {
    void ViewRandomMeal(List<MealModel> models);
    void ViewCountriesList(List<Country> models);
    void ViewCategoriesList(List<Category> models);
    public void addToFavorite (MealModel mealModel);

    void insertDataInRoom(MealModel mealModel);
}
