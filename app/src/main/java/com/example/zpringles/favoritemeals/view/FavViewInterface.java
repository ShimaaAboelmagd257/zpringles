package com.example.zpringles.favoritemeals.view;

import com.example.zpringles.model.MealModel;
import com.example.zpringles.model.modelFirebase.UserModel;

import java.util.List;

public interface FavViewInterface {
    void removeFromFav(MealModel meal);

    public void showData();

    public void updateFavoriteInFirebase(UserModel userModel);

}
