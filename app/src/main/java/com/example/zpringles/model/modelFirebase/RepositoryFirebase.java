package com.example.zpringles.model.modelFirebase;

import android.content.Context;

import com.example.zpringles.DataBaseHandling.firebase.FirebaseSource;
import com.example.zpringles.DataBaseHandling.sharedpreference.SharedPreferenceSource;

public class RepositoryFirebase implements RepositoryFirebaseInterface {

    private Context context;
    private FirebaseSource firebaseSource;
    private SharedPreferenceSource sharedPreferenceSource;
    private static RepositoryFirebase repository = null;



    public RepositoryFirebase(FirebaseSource firebaseSource,SharedPreferenceSource sharedPreferenceSource, Context context) {
        this.context = context;
        this.firebaseSource = firebaseSource;
        this.sharedPreferenceSource = sharedPreferenceSource ;
    }
    public static RepositoryFirebase getInstance(FirebaseSource firebaseSource,SharedPreferenceSource sharedPreferenceSource, Context context){
        if (repository == null){
            repository = new RepositoryFirebase(firebaseSource,sharedPreferenceSource,context);
        }

        return  repository;
    }



    @Override
    public void SignUpWithGoogle(UserModel userModel) {

    }

    @Override
    public void signUpWithCreateEmail(UserModel userModel) {
        firebaseSource.insertUser(userModel);
    }



    @Override
    public void saveUserData(UserModel userModel) {
        sharedPreferenceSource.saveUserData(userModel);
    }

    @Override
    public boolean isUserExists(UserModel userModel) {

        return  firebaseSource.isUserExists(userModel);
    }

    @Override
    public boolean isLoginSuccessed(Context context,String email, String pass) {
        return firebaseSource.isLoginSuccessed(context,email,pass);
    }

    @Override
    public UserModel getSavedUserData() {
        return sharedPreferenceSource.getSavedUserData();
    }

    @Override
    public void updateUserData(UserModel userModel) {
        sharedPreferenceSource.updateUserData(userModel);
    }

    @Override
    public void updateUserFirebaseData(UserModel userModel) {
        firebaseSource.insertUser(userModel);
    }

    @Override
    public void updateFavoriteInFirebase(UserModel userModel) {
        firebaseSource.insertUser(userModel);
    }

    @Override
    public void uploadPlanInFirebase(UserModel userModel) {
        firebaseSource.insertUser(userModel);
    }



}
