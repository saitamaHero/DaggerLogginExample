package com.lolisoft.daggerloginexample.root;

import com.lolisoft.daggerloginexample.login.LoginActivity;
import com.lolisoft.daggerloginexample.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity target);
}
