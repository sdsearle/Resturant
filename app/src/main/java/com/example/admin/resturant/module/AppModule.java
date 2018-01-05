package com.example.admin.resturant.module;

import com.example.admin.resturant.View.Login.LoginAuthenticator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 1/5/2018.
 */

@Module
public class AppModule {

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    public AppModule(FirebaseDatabase firebaseDatabase, FirebaseAuth firebaseAuth) {

        this.firebaseDatabase = firebaseDatabase;

        this.firebaseAuth = firebaseAuth;

    }

    @Provides
    LoginAuthenticator providesLoginAuthenticator() {

        return new LoginAuthenticator(firebaseAuth);

    }

}