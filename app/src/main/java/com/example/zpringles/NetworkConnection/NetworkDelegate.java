package com.example.zpringles.NetworkConnection;

import com.example.zpringles.model.retrofit.Category;
import com.example.zpringles.model.retrofit.Country;
import com.example.zpringles.model.retrofit.Ingredient;
import com.example.zpringles.model.MealModel;

import java.util.List;


public interface NetworkDelegate {

    void onSuccessMeals(List<MealModel> mealModels);

    public void onSuccessCategories(List<Category> categoryList);

    public void onSuccessCountries(List<Country> countries);

    public void onSuccessIngredients(List<Ingredient> ingredients);

    public void onFailurResult(String message);
}
