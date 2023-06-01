package com.example.zpringles.planmeals.presenter;

import com.example.zpringles.model.MealModel;
import com.example.zpringles.model.modelFirebase.UserModel;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface PlanPresenterInterface {
    Single<List<MealModel>> getAllPlanedMeals(String day);
    void removeFromPlan(MealModel mealModel);
    void uploadPlanInFirebase(UserModel userModel);

    UserModel getSavedData();

    public void addToFavorite(MealModel mealModel);
}
