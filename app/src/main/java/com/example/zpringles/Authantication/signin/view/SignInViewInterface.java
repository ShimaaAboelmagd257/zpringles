package com.example.zpringles.Authantication.signin.view;

import android.content.Context;

import com.example.zpringles.model.modelFirebase.UserModel;

public interface SignInViewInterface {
    void onLoginSuccess(UserModel userModel);
    boolean isSuccessed(Context context, String email, String pass);
}
