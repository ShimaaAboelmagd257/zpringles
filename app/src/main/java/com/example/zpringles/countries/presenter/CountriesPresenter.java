package com.example.zpringles.countries.presenter;

import com.example.zpringles.countries.view.CountriesViewInterface;
import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.POJO.Ingredient;
import com.example.zpringles.model.RepositoryInterface;
import com.example.zpringles.NetworkConnection.NetworkDelegate;

import java.util.List;

public class CountriesPresenter implements CountriesPresenterInterface,NetworkDelegate {
    CountriesViewInterface _view;
    RepositoryInterface _repo;
    public CountriesPresenter(CountriesViewInterface view, RepositoryInterface repo){
        this._repo=repo;
        this._view=view;

    }
    @Override
    public void getMeals(String countryName) {
            _repo.getMealsByCountries(this,countryName);
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        _repo.insertFavorite(mealModel);

    }

    @Override
    public void onSuccessMeals(List<MealModel> mealModels) {
        _view.ViewCounteryMeal(mealModels);
    }

    @Override
    public void onSuccessCategories(List<Category> categoryList) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {


    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {

    }

    @Override
    public void onFailurResult(String message) {

    }
}
