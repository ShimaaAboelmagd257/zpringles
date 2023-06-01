package com.example.zpringles.itemPage.presenter;

import com.example.zpringles.itemPage.view.ItemViewInterface;
import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.POJO.Category;
import com.example.zpringles.model.POJO.Country;
import com.example.zpringles.model.POJO.Ingredient;
import com.example.zpringles.model.RepositoryInterface;
import com.example.zpringles.NetworkConnection.NetworkDelegate;

import java.util.List;

public class ItemPagePresenter implements ItemPagePresenterInterface, NetworkDelegate {
    ItemViewInterface _view;
    RepositoryInterface _repo;

    public ItemPagePresenter(ItemViewInterface _view, RepositoryInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        _repo.insertFavorite(mealModel);
    }

    @Override
    public void addToPlan(MealModel mealModel) {
        _repo.insertPlan(mealModel);
    }

    @Override
    public void onSuccessMeals(List<MealModel> mealModels) {

        _view.ViewMealItem(mealModels);

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

    @Override
    public void getMealItem(String ItemName) {
        System.out.println("getMealItem meal Models : "+ ItemName);
        _repo.getMealsByName(this,ItemName);
    }


}
