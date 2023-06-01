package com.example.zpringles.favoritemeals.view;

import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.modelFirebase.UserModel;

public interface FavViewInterface {
    void removeFromFav(MealModel meal);

    public void showData();

    public void updateFavoriteInFirebase(UserModel userModel);

}
