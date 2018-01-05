package com.example.admin.resturant.View.Login;

import com.example.admin.resturant.BasePresenter;
import com.example.admin.resturant.BaseView;

/**
 * Created by admin on 12/27/2017.
 */

public interface LoginContract {
    interface View extends BaseView {



        void onUserCreation(boolean isCreated);



        void onUserValidation(boolean isValid);



        void isSessionValid(boolean isValid);



    }



    interface Presenter extends BasePresenter<View> {



        void validateUser(String email, String password);



        void createUser(String email, String password);



        void checkSession();


    }
}
