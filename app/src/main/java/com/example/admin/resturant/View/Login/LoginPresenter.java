package com.example.admin.resturant.View.Login;

/**
 * Created by admin on 12/27/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginAuthenticator loginAuthenticator;

    public LoginPresenter(LoginAuthenticator loginAuthenticator){
        this.loginAuthenticator = loginAuthenticator;
        loginAuthenticator.attach(this);
    }

    @Override
    public void attachView(LoginContract.View view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void validateUser(String email, String password) {
        loginAuthenticator.loginUser(email, password, this);
    }

    @Override
    public void createUser(String email, String password) {

    }

    @Override
    public void checkSession() {

    }
}
