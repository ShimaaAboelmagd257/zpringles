package com.example.zpringles.categories.presenter;

import com.example.zpringles.categories.view.CategoriesViewInterface;
import com.example.zpringles.categories.view.CategoryFragment;

import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.POJO.Ingredient;
import com.example.zpringles.model.RepositoryInterface;
import com.example.zpringles.NetworkConnection.NetworkDelegate;
import com.example.zpringles.model.Repository;

import java.util.List;

public class CategoriesPresenter implements CategoriesPresenterInterface, NetworkDelegate {
    CategoriesViewInterface _view;
    RepositoryInterface _repo;
    public CategoriesPresenter(CategoryFragment view, Repository repo){
        this._repo=repo;
        this._view=view;

    }

    @Override
    public void getMeals(String category) {
        _repo.getMealsByCategories(this,category);
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        _repo.insertFavorite(mealModel);

    }

    @Override
    public void onSuccessMeals(List<MealModel> mealModels) {
        _view.ViewCategoriesMeal(mealModels);
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
