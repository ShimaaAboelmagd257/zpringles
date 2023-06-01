package com.example.zpringles.DataBaseHandling.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.zpringles.model.modelFirebase.UserModel;
import com.example.zpringles.utalites.Helper;

public class SharedPreferenceSource implements SharedPreferenceSourceInterfece{

    private static SharedPreferenceSource sharedPreferenceSource = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private UserModel userModel ;

    private SharedPreferenceSource(Context context){

        sharedPreferences = context.getSharedPreferences(Helper.SHARDPREFERENCE,context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userModel = new UserModel();

    }

    public static SharedPreferenceSource getInstance(Context context){

        if (sharedPreferenceSource == null) {
            sharedPreferenceSource = new SharedPreferenceSource(context);
        }
        return sharedPreferenceSource;
    }

    @Override
    public void saveUserData(UserModel userModel) {
        editor.putString(Helper.USERNAME,userModel.getUserName());
        editor.putString(Helper.PASSWORD,userModel.getPassWord());
        editor.putString(Helper.EMAIL,userModel.getEmail());
        editor.commit();


    }


    @Override
    public UserModel getSavedUserData() {
        userModel.setUserName(sharedPreferences.getString(Helper.USERNAME,"Null"));
        userModel.setEmail(sharedPreferences.getString(Helper.EMAIL,"Null"));
        userModel.setPassWord(sharedPreferences.getString(Helper.PASSWORD,"Null"));
        userModel.setImage(sharedPreferences.getString(Helper.IMAGE,"null"));
        return userModel;


    }

    @Override
    public void updateUserData(UserModel userModel) {
        editor.putString(Helper.USERNAME,userModel.getUserName());
        editor.putString(Helper.PASSWORD,userModel.getPassWord());
        editor.putString(Helper.EMAIL,userModel.getEmail());
        editor.putString(Helper.IMAGE,userModel.getImage());
        editor.commit();

    }
}
