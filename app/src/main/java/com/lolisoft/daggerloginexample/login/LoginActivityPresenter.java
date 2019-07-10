package com.lolisoft.daggerloginexample.login;

import android.support.annotation.Nullable;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    @Nullable
    private LoginActivityMVP.View mView;
    private LoginActivityMVP.Model mModel;


    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.mModel = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.mView = view;
    }

    @Override
    public void onLoginButtonClick() {
        if(this.mView != null){
            if(this.mView.getName().trim().length() == 0  || this.mView.getLastName().trim().length() == 0){
                this.mView.showEmptyField();
            }else{
                this.mModel.createUser(this.mView.getName().trim(), this.mView.getLastName().trim());
                this.mView.showUserSaved();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = this.mModel.getUser();

        if(user == null && this.mView != null){
            this.mView.showUserNotValid();
        }else if(this.mView != null){
            this.mView.setName(user.getName());
            this.mView.setLastName(user.getLastname());
        }

    }
}
