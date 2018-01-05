package com.example.admin.resturant.module;

import android.app.Activity;

import com.example.admin.resturant.View.Login.LoginAuthenticator;
import com.example.admin.resturant.View.Login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 1/5/2018.
 */

@Module
public class LoginModule {

    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //add the dependencies using the @provides for each method
    @Provides
    LoginPresenter providesLoginPresenter(LoginAuthenticator loginAuthenticator){
        loginAuthenticator.attach(activity);
        return new LoginPresenter(loginAuthenticator);
    }
}
