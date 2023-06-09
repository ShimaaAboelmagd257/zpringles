package com.example.zpringles.planmeals.presenter;

import com.example.zpringles.model.POJO.MealModel;
import com.example.zpringles.model.modelFirebase.RepositoryFirebaseInterface;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.example.zpringles.model.RepositoryInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class PlanMealsPresenter implements PlanPresenterInterface {
    private RepositoryInterface repositoryInterface;

    private RepositoryFirebaseInterface repositoryFirebaseInterface;



    // the constructor of the class presenter dealling with askig the repo to fetch for him this data
    public PlanMealsPresenter(RepositoryInterface repositoryInterface,RepositoryFirebaseInterface repositoryFirebaseInterface){
        this.repositoryInterface = repositoryInterface ;
        this.repositoryFirebaseInterface=repositoryFirebaseInterface;
    }

    @Override
    public Single<List<MealModel>> getAllPlanedMeals(String day) {
        return repositoryInterface.getAllStoredPlans( day);
    }

    @Override
    public void removeFromPlan(MealModel mealModel) {
        repositoryInterface.removePlan(mealModel);

    }

    @Override
    public void uploadPlanInFirebase(UserModel userModel) {
        repositoryFirebaseInterface.uploadPlanInFirebase(userModel);
    }

    @Override
    public UserModel getSavedData() {
        return repositoryFirebaseInterface.getSavedUserData();
    }

    @Override
    public void addToFavorite(MealModel mealModel) {
        repositoryInterface.insertFavorite(mealModel);
    }


}
