package com.example.zpringles.search.presenter;

import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.POJO.Ingredient;
import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.RepositoryInterface;
import com.example.zpringles.NetworkConnection.NetworkDelegate;
import com.example.zpringles.search.view.SearchViewInterface;

import java.util.List;

public class SearchPresenter implements SearchPresenterInterface , NetworkDelegate {

    private SearchViewInterface searchViewInterface;
    private RepositoryInterface repositoryInterface;


    public SearchPresenter(SearchViewInterface searchViewInterface, RepositoryInterface repositoryInterface ) {
        this.searchViewInterface = searchViewInterface;
        this.repositoryInterface = repositoryInterface;
    }


    @Override
    public void onSuccessMeals(List<MealModel> mealModels) {
        searchViewInterface.getMeals(mealModels);
    }

    @Override
    public void onSuccessCategories(List<Category> categoryList) {
        searchViewInterface.getAllCategories(categoryList);
    }

    @Override
    public void onSuccessCountries(List<Country> countries) {
        searchViewInterface.getAllCountries(countries);
    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {
        searchViewInterface.getAllIngredient(ingredients);
    }

    @Override
    public void onFailurResult(String message) {

    }

    @Override
    public void getAllCategories() {
        repositoryInterface.getAllCategories(this);
    }

    @Override
    public void getAllCountries() {
        repositoryInterface.getAllCountries(this);
    }

    @Override
    public void getAllIngredient() {
        repositoryInterface.getAllIngredient(this);
    }

    @Override
    public void getMealsByName(String name) {
        repositoryInterface.getMealsByFirstChar(this,name);
    }

    @Override
    public void getMealsByCategories(String category) {
        repositoryInterface.getMealsByCategories(this,category);
    }

    @Override
    public void getMealsByCountries(String country) {
        repositoryInterface.getMealsByCountries(this,country);
    }

    @Override
    public void getMealsByIngredients(String ingredient) {
        repositoryInterface.getMealsByIngredients(this,ingredient);
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        repositoryInterface.insertFavorite(mealModel);

    }
}
