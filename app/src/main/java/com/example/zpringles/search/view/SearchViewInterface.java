package com.example.zpringles.search.view;

import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.POJO.Ingredient;
import com.example.zpringles.model.POJO.MealModel;

import java.util.List;

public interface SearchViewInterface {

    public void getMeal(List<MealModel> Meals);

    public void getAllCategories(List<Category> categories);

    public void getAllCountries(List<Country> countries);

    public void getAllIngredient(List<Ingredient> ingredients);

    public void getMeals(List<MealModel> mealModels);

    public void addToFavorite (MealModel mealModel);

}
