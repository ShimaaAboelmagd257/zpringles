package com.example.zpringles.Authantication.signin.presenter;

import android.content.Context;

import com.example.zpringles.model.modelFirebase.UserModel;

public interface SigninPresenterInterface {
    void addUserDataToShered(UserModel userModel);
    boolean checkUserData(Context context, String email, String pass);
}
