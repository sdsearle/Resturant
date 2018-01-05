package com.example.admin.resturant.View.Login;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.admin.resturant.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by admin on 12/27/2017.
 */

public class LoginAuthenticator {

    private FirebaseAuth mAuth;
    private OnLoginInteraction onLoginInteraction;
    private Activity activity;
    private String TAG = "loginAuthTag";
    private FirebaseUser user;

    public LoginAuthenticator(FirebaseAuth auth) {

        this.mAuth = auth;

    }

    public void attach(Object object) {

        if (object instanceof BasePresenter) {

            if (object instanceof LoginPresenter)

                onLoginInteraction = (OnLoginInteraction) object;

        }



        if (object instanceof Activity)

            activity = (Activity) object;

    }

    public void loginUser(String email, String password, final LoginPresenter presenter){

        /*PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "4802681516",        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                LoginActivity.this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks*/

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    onLoginInteraction.onUserValidation(user);
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());

                                } else {
                                    user = mAuth.getCurrentUser();
                                    onLoginInteraction.onUserValidation(user);

                                    //user = FirebaseAuth.getInstance().getCurrentUser();
                                    if(mAuth.getCurrentUser().getPhoneNumber() == null){



                                    }//else {

                                    //}
                                    //tvUser.setText("signed in");
                                }
                                // ...
                            }
                        });
    }

    public void createUser(String email, String password, LoginPresenter presenter){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            //FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            //Log.d(TAG, "onComplete: " + e.getMessage());
                            onLoginInteraction.onUserCreation(user);
                        } else
                            onLoginInteraction.onUserCreation(user);

                        // ...
                    }
                }).addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }
        });
    }

    public void attach(LoginPresenter presenter) {
    }

    interface OnLoginInteraction {



        void onUserCreation(FirebaseUser user);



        void onUserValidation(FirebaseUser user);



        void onSessionValid(boolean isValid);



    }
}
