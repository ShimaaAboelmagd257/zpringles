package com.example.zpringles.Authantication.signup.presenter;

import com.example.zpringles.Authantication.signup.view.SignUpViewInterface;
import com.example.zpringles.model.modelFirebase.RepositoryFirebaseInterface;
import com.example.zpringles.model.modelFirebase.UserModel;

public class SignupPresenter implements SignUpPresenterInterface {

    private RepositoryFirebaseInterface repo;

    private SignUpViewInterface _view;

    public SignupPresenter(RepositoryFirebaseInterface repo){
        this.repo = repo;

    }

    @Override
    public void onSuccessSignUpWithGoogle() {

    }

    @Override
    public void onFailureSignUpWithGoogle(String error) {

    }

    @Override
    public void addUserData(UserModel userModel) {
        repo.signUpWithCreateEmail(userModel);
    }


    @Override
    public void saveUserData(UserModel userModel) {
        repo.saveUserData(userModel);
    }


    @Override
    public boolean isUserExists(UserModel userModel) {
        return repo.isUserExists(userModel);
    }
}
