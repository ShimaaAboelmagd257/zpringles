package com.example.zpringles.Authantication.signup.presenter;

import com.example.zpringles.Authantication.signup.view.SignUpViewInterface;
import com.example.zpringles.model.modelFirebase.RepositoryFirebaseInterface;
import com.example.zpringles.model.modelFirebase.UserModel;

public class SignupPresenter implements SignUpPresenterInterface {

    private RepositoryFirebaseInterface _repo;

    private SignUpViewInterface _view;

    public SignupPresenter(RepositoryFirebaseInterface _repo){
        this._repo = _repo;

    }

    @Override
    public void onSuccessSignUpWithGoogle() {

    }

    @Override
    public void onFailureSignUpWithGoogle(String error) {

    }

    @Override
    public void addUserData(UserModel userModel) {
        _repo.signUpWithCreateEmail(userModel);
    }


    @Override
    public void saveUserData(UserModel userModel) {
        _repo.saveUserData(userModel);
    }


    @Override
    public boolean isUserExists(UserModel userModel) {
        return _repo.isUserExists(userModel);
    }
}
