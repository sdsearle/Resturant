package com.example.admin.resturant.component;

import com.example.admin.resturant.module.AppModule;
import com.example.admin.resturant.module.LoginModule;

import dagger.Component;

/**
 * Created by admin on 1/5/2018.
 */


@Component(modules = AppModule.class)
public interface AppComponent {
    LoginComponent add(LoginModule loginModule);
}
