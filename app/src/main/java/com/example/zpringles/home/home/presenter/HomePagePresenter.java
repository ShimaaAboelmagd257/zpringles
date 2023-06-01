package com.example.zpringles.home.home.presenter;

import com.example.zpringles.home.home.view.HomeViewInterface;

import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.POJO.Ingredient;
import com.example.zpringles.model.RepositoryInterface;
import com.example.zpringles.NetworkConnection.NetworkDelegate;

import java.util.List;

public class HomePagePresenter implements NetworkDelegate ,HomePresenter {

    HomeViewInterface _view;
    RepositoryInterface repo;

    public HomePagePresenter(HomeViewInterface view, RepositoryInterface repo){
        this.repo=repo;
        this._view=view;

    }

    @Override
    public void onSuccessMeals(List<MealModel> mealModels) {
            _view.ViewRandomMeal(mealModels);
    }

    @Override
    public void onSuccessCategories(List<Category> categoryList) {
            _view.ViewCategoriesList(categoryList);
    }

    @Override
    public void onSuccessCountries(List<Country> countries) {
        _view.ViewCountriesList(countries);
    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {

    }

    @Override
    public void onFailurResult(String message) {
        System.out.println("Error when get data in home : "+message);
    }

    @Override
    public void getRandomMeal() {
        repo.getRandomMeal(this);
    }

    @Override
    public void getCountriesList() {
        repo.getAllCountries(this);
    }

    @Override
    public void getCategoriesList() {
        repo.getAllCategories(this);
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        repo.insertFavorite(mealModel);
    }

    @Override
    public void insertDataInRoom(MealModel mealModel) {
        repo.insertDataInRoom(mealModel);
    }
}
