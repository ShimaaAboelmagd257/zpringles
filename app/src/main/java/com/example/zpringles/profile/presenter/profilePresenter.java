package com.example.zpringles.profile.presenter;

import com.example.zpringles.model.modelFirebase.RepositoryFirebaseInterface;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.example.zpringles.model.retrofit.RepositoryInterface;
import com.example.zpringles.profile.view.ProfileViewInterface;

public class profilePresenter implements profilePresenterInterface{

    private RepositoryFirebaseInterface repositoryFirebaseInterface;

    private RepositoryInterface repositoryInterface;

    private ProfileViewInterface profileViewInterface;

    public profilePresenter(RepositoryFirebaseInterface repositoryFirebaseInterface , RepositoryInterface repositoryInterface){
        this.repositoryFirebaseInterface = repositoryFirebaseInterface;
        this.repositoryInterface = repositoryInterface;
    }
    @Override
    public UserModel getSavedUserData() {
        return repositoryFirebaseInterface.getSavedUserData();
    }


    @Override
    public void updateUserData(UserModel userModel) {
        repositoryFirebaseInterface.updateUserData(userModel);
    }

    @Override
    public void updateUserFirebaseData(UserModel userModel) {
        repositoryFirebaseInterface.updateUserFirebaseData(userModel);
    }

    @Override
    public void deleteAllMeals() {
        repositoryInterface.deleteAllMeals();
    }
}
