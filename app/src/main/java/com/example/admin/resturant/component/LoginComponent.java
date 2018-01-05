package com.example.admin.resturant.component;

import com.example.admin.resturant.View.Login.LoginActivity;
import com.example.admin.resturant.module.LoginModule;

import dagger.Subcomponent;

/**
 * Created by admin on 1/5/2018.
 */

@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {

    //inject the activity reference for the object
    void inject(LoginActivity loginActivity);
}
