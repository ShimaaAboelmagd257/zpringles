package com.example.zpringles.Authantication.signin.presenter;

import android.content.Context;

import com.example.zpringles.Authantication.signin.view.SignInViewInterface;
import com.example.zpringles.model.modelFirebase.RepositoryFirebaseInterface;
import com.example.zpringles.model.modelFirebase.UserModel;

public class SigninPresenter implements SigninPresenterInterface{

    private RepositoryFirebaseInterface _repo;

    private SignInViewInterface _view;

    public SigninPresenter(SignInViewInterface view, RepositoryFirebaseInterface repo){
        _repo=repo;
        _view=view;
    }
    @Override
    public void addUserDataToShered(UserModel userModel) {
        _repo.saveUserData(userModel);
    }

    @Override
    public boolean checkUserData(Context context, String email, String pass) {
        return _repo.isLoginSuccessed(context,email,pass);
    }
}
