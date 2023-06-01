package com.example.zpringles.model;

import android.content.Context;

import com.example.zpringles.DataBaseHandling.room.LocalSource;
import com.example.zpringles.home.home.presenter.HomePagePresenter;
import com.example.zpringles.NetworkConnection.NetworkDelegate;
import com.example.zpringles.NetworkConnection.RemoteSource;
import com.example.zpringles.DataBaseHandling.room.ConceretLocalSource;
import com.example.zpringles.NetworkConnection.APIClient;
import com.example.zpringles.model.POJO.MealModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class Repository implements RepositoryInterface{

    private Context context ;

    RemoteSource remoteSource;
    private static Repository repository =null ;

    LocalSource localSource ;

    public Repository(RemoteSource remoteSource,LocalSource localSource,Context context) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.localSource = localSource;

    }

    public static Repository getInstance(APIClient remoteSource, ConceretLocalSource localSource, Context context){
        if (repository == null){
            repository = new Repository(remoteSource,localSource,context);
        }

        return  repository;
    }


    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        remoteSource.getRandomMeals(networkDelegate);
    }

    @Override
    public void getAllCategories(NetworkDelegate networkDelegate) {
        remoteSource.getAllCategories(networkDelegate);
    }

    @Override
    public void getAllCountries(NetworkDelegate networkDelegate) {
        remoteSource.getAllCountries(networkDelegate);
    }

    @Override
    public void getAllIngredient(NetworkDelegate networkDelegate) {
        remoteSource.getAllIngredient(networkDelegate);
    }

    @Override
    public void getMealsByName(NetworkDelegate networkDelegate, String name) {
        remoteSource.getMealsByName(networkDelegate,name);
    }

    @Override
    public void getMealsByFirstChar(NetworkDelegate networkDelegate, String name) {
        remoteSource.getMealsByFirstChar(networkDelegate,name);
    }

    @Override
    public void getMealsByCategories(NetworkDelegate networkDelegate, String category) {
        remoteSource.getMealsByCategories(networkDelegate,category);
    }

    @Override
    public void getMealsByCountries(NetworkDelegate networkDelegate, String country) {
        remoteSource.getMealsByCountries(networkDelegate,country);
    }

    @Override
    public void getMealsByIngredients(NetworkDelegate networkDelegate, String ingredient) {
        remoteSource.getMealsByIngredients(networkDelegate,ingredient);
    }

    @Override
    public void insertFavorite(MealModel mealModel) {
        localSource.insertFavorite(mealModel);
    }

    @Override
    public void removeFavorite(MealModel mealModel) {
        localSource.removeFavorite(mealModel);
    }

    @Override
    public Single<List<MealModel>> getAllStoredFavorites() {
        return localSource.getAllStoredFavorites();
    }

    @Override
    public void insertPlan(MealModel mealModel) {
        localSource.insertPlan(mealModel);

    }

    @Override
    public void removePlan(MealModel mealModel) {
        localSource.removePlan(mealModel);

    }

    @Override
    public Single<List<MealModel>> getAllStoredPlans(String day) {
        return localSource.getAllStoredPlans(day);
    }

    @Override
    public void deleteAllMeals() {
        localSource.deleteAllMeals();
    }

    @Override
    public void insertDataInRoom(MealModel mealModel) {
        localSource.insertFavorite(mealModel);
    }

    @Override
    public void getRandomMeal(HomePagePresenter homePagePresenter) {

    }


}
