package com.example.admin.resturant.View.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.resturant.Item;
import com.example.admin.resturant.R;
import com.example.admin.resturant.View.Resturant.MenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    TextView tvUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvUser = findViewById(R.id.tvUser);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //User is signed in
                    tvUser.setText(user.getEmail());
                    Log.d(TAG, "onAuthStateChanged: signedIn: " + user.getUid());
                } else {
                    tvUser.setText("Not signed in");
                    Log.d(TAG, "onAuthStateChanged: signedOut");
                }
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void usingFirebase(View view) {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        switch (view.getId()) {

            case R.id.btnLogin:

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(LoginActivity.this, "Sign in failed",
                                            Toast.LENGTH_SHORT).show();
                                    tvUser.setText("failed to sign in");
                                } else {
                                    Toast.makeText(LoginActivity.this, "SignIn Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                    tvUser.setText("signed in");
                                }
                                // ...
                            }
                        });


                break;

            case R.id.btnSignup:
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    //FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                    //Log.d(TAG, "onComplete: " + e.getMessage());
                                    Toast.makeText(LoginActivity.this, "Creation Failed", Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(LoginActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                                // ...
                            }
                        }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });
                break;

            case R.id.btnSignOut:
                    mAuth.signOut();
                    tvUser.setText("not Signed In");
                break;
        }
    }

    public void createData(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("menu");

        List<Item> items = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Item hamburger = new Item(i + " Patty Burger",
                    1 + (2*i)/3.0 + "");

            items.add(hamburger);

            Item cheseburger = new Item(i + " by " + (i/2 + 1) + " Burger",
                    (1 + (2*i)/2.0) + "");

            items.add(cheseburger);

            Item nuggets = new Item(i * 5 + " piece nuggets",
                    1 + (2*i)/4.0 + "");

            items.add(nuggets);

        }
        for (Item item:
             items) {

            myRef.push().setValue(item)
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());
                        }
                    });
        }

    }
}
