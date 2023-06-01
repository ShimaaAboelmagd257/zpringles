package com.example.zpringles.DataBaseHandling.firebase;

import android.content.Context;

import com.example.zpringles.model.modelFirebase.UserModel;

public interface FirebaseSourseInterface {

    void insertUser(UserModel userModel);
    boolean isUserExists(UserModel userModel);

    boolean isLoginSuccessed(Context context, String email, String pass);



}
