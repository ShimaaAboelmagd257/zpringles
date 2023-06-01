package com.example.zpringles.Authantication.signup.presenter;

import com.example.zpringles.model.modelFirebase.UserModel;

public interface SignUpPresenterInterface {
    void onSuccessSignUpWithGoogle();
    void onFailureSignUpWithGoogle(String error);

    void addUserData(UserModel userModel) ;

    void saveUserData(UserModel userModel);


    boolean isUserExists(UserModel userModel);
}
