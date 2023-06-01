package com.example.zpringles.NetworkConnection;

public interface RemoteSource {

    void getRandomMeals(NetworkDelegate networkDelegate);

    void getAllCategories(NetworkDelegate networkDelegate);

    void getAllCountries(NetworkDelegate networkDelegate);

    void getAllIngredient(NetworkDelegate networkDelegate);

    void getMealsByFirstChar(NetworkDelegate networkDelegate, String name);

    void getMealsByCategories(NetworkDelegate networkDelegate, String category);

    void getMealsByCountries(NetworkDelegate networkDelegate, String country);

    void getMealsByIngredients(NetworkDelegate networkDelegate, String ingredient);

    void  getMealsByName(NetworkDelegate networkDelegate,String name);


}
