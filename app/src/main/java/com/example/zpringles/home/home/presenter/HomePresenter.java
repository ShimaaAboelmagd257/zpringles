package com.example.zpringles.home.home.presenter;


import com.example.zpringles.model.MealModel;

public interface HomePresenter {
    void getRandomMeal();
    void getCountriesList();
    void getCategoriesList();

    public void addToFavorite(MealModel mealModel);

    void insertDataInRoom(MealModel mealModel);

}
