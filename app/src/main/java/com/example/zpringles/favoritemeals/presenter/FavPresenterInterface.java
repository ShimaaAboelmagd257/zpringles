package com.example.zpringles.favoritemeals.presenter;

import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.modelFirebase.UserModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface FavPresenterInterface {
    Single<List<MealModel>> getAllStoredFavorites();
    void removeFromFavorite(MealModel mealModel);

    void updateFavoriteInFirebase(UserModel userModel);

    UserModel getSavedData();
}
