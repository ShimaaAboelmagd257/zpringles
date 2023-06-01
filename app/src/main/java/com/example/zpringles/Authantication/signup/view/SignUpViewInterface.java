package com.example.zpringles.Authantication.signup.view;

import androidx.annotation.NonNull;

import com.example.zpringles.model.modelFirebase.UserModel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public interface SignUpViewInterface {

    void onSuccessSignUpWithGoogle();
    void onFailureSignUpWithGoogle(String error);
    void insertUserData(UserModel userModel);

    boolean isUserExists(UserModel userModel);

    void saveUserData(UserModel userModel);

    void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) throws ApiException;

    void FirebaseGoogleAuth(@NonNull GoogleSignInAccount account);
}
