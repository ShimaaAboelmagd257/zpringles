package com.example.zpringles.profile.view;

import com.example.zpringles.model.modelFirebase.UserModel;

import java.util.List;

public interface ProfileViewInterface {

    UserModel getSavedUserData();

    void updateUserData(UserModel userModel);

    void updateUserFirebaseData(UserModel userModel);

    public void showData(UserModel userModel);
}
