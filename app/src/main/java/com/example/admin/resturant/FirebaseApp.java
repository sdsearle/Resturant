package com.example.admin.resturant;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.admin.resturant.component.AppComponent;
import com.example.admin.resturant.component.LoginComponent;
import com.example.admin.resturant.module.AppModule;
import com.example.admin.resturant.module.LoginModule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by admin on 1/5/2018.
 */

public class FirebaseApp extends Application {
    AppComponent appComponent;
    LoginComponent loginComponent;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private LoginModule loginModule;

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        AppModule appModule = new AppModule(firebaseDatabase, firebaseAuth);
        /*appComponent = DaggerAppComponent.builder()
                .appModule(appModule)
                .build();*/

        createLoginComponent();

    }

    public static FirebaseApp get(Context context) {
        return (FirebaseApp) context.getApplicationContext();
    }

    public void createLoginComponent(){
        loginModule = new LoginModule();
        loginComponent = appComponent.add(loginModule);
    }

    public LoginComponent getLoginComponent(Activity activity) {
        loginModule.setActivity(activity);
        return loginComponent;
    }

    public void clearLoginComponent() {
        loginComponent = null;
    }

}