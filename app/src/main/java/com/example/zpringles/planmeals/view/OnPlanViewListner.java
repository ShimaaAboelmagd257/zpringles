package com.example.zpringles.planmeals.view;

import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.modelFirebase.UserModel;

import java.util.List;

public interface OnPlanViewListner {
    void removeMealFromPlaned(MealModel meal);

    public void ViewData(List<MealModel> Meals);

    public void uploadPlanInFirebase(UserModel userModel);

    public void addToFavorite (MealModel mealModel);
}
