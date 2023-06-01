package com.example.zpringles.DataBaseHandling.room;


import com.example.zpringles.model.MealModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface LocalSource {

    void insertFavorite(MealModel mealModel);
    void removeFavorite(MealModel mealModel);
    Single<List<MealModel>> getAllStoredFavorites();
    void insertPlan(MealModel mealModel);
    void removePlan(MealModel mealModel);
    Single<List<MealModel>> getAllStoredPlans(String day);
    public void deleteAllMeals() ;
}
