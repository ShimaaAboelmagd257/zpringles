package com.example.zpringles.profile.presenter;

import com.example.zpringles.model.modelFirebase.UserModel;

public interface profilePresenterInterface {
    UserModel getSavedUserData();
    void updateUserData(UserModel userModel);

    void updateUserFirebaseData(UserModel userModel);

    public void deleteAllMeals() ;

}
