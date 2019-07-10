package com.lolisoft.daggerloginexample.login;

public interface LoginActivityMVP {

    interface View{
        String getName();
        String getLastName();

        void showUserNotValid();
        void showEmptyField();
        void showUserSaved();

        void setName(String name);
        void setLastName(String lastName);
    }

    interface Presenter{
        void setView(LoginActivityMVP.View view);

        void onLoginButtonClick();

        void getCurrentUser();
    }

    interface Model{
        void createUser(String name, String lastName);

        User getUser();
    }
}
