package com.example.zpringles.DataBaseHandling.sharedpreference;

import com.example.zpringles.model.modelFirebase.UserModel;

public interface SharedPreferenceSourceInterfece {

    void saveUserData(UserModel userModel);

    UserModel getSavedUserData();

    void updateUserData(UserModel userModel);



}
