package com.example.zpringles.search.view;


import com.example.zpringles.model.POJO.MealModel;

public interface SearchClickListener {

    void categoryItemOnClick(String category);
    void countryItemOnClick(String country);
    void ingredientItemOnclick(String ingredient);
    void categoryOnClick();
    void countryOnClick();
    void ingredientOnclick();

    void addToFavoriteOnClick(MealModel mealModel);


}
