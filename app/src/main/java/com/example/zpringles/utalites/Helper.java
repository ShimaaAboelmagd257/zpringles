package com.example.zpringles.utalites;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.zpringles.model.MealModel;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static List<MealModel> FavUserMealsList=new ArrayList<>();
    public static final String SHARDPREFERENCE = "SharedPreference";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    public static String SKIP ;
    public static final String IMAGE = "IMAGE";
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";
}
