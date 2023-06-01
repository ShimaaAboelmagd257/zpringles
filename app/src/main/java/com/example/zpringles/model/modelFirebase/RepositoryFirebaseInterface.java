package com.example.zpringles.model.modelFirebase;

import android.app.Activity;
import android.content.Context;

public interface RepositoryFirebaseInterface {

    public void SignUpWithGoogle(UserModel userModel);
    public void signUpWithCreateEmail(UserModel userModel);

    void saveUserData(UserModel userModel);

    boolean isUserExists(UserModel userModel);

    boolean isLoginSuccessed(Context context, String email, String pass);

    UserModel getSavedUserData();

    void updateUserData(UserModel userModel);

    void updateUserFirebaseData(UserModel userModel);

    void updateFavoriteInFirebase(UserModel userModel);

    void uploadPlanInFirebase(UserModel userModel);


}
