package com.example.zpringles.favoritemeals.presenter;

import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.modelFirebase.RepositoryFirebaseInterface;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.example.zpringles.model.RepositoryInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class FavoriteMealsPresenter implements FavPresenterInterface{
    private RepositoryInterface repositoryInterface;

    private RepositoryFirebaseInterface repositoryFirebaseInterface;


    public FavoriteMealsPresenter(RepositoryInterface repositoryInterface,RepositoryFirebaseInterface repositoryFirebaseInterface){
        this.repositoryInterface = repositoryInterface ;
        this.repositoryFirebaseInterface = repositoryFirebaseInterface;
    }


    @Override
    public Single<List<MealModel>> getAllStoredFavorites() {
        return repositoryInterface.getAllStoredFavorites();
    }

    @Override
    public void removeFromFavorite(MealModel mealModel) {
        repositoryInterface.removeFavorite(mealModel);
    }

    @Override
    public void updateFavoriteInFirebase(UserModel userModel) {
        repositoryFirebaseInterface.updateFavoriteInFirebase(userModel);
    }

    @Override
    public UserModel getSavedData() {
        return repositoryFirebaseInterface.getSavedUserData();
    }
}
