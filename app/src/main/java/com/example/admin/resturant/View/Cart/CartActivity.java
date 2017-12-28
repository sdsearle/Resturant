package com.example.admin.resturant.View.Cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.admin.resturant.Item;
import com.example.admin.resturant.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity implements MyCartItemRecyclerViewAdapter.OnListInteractionListener{

    private int mColumnCount = 1;
    private String TAG = "MenuActivityTag";
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in

            myRef = database.getReference("users").child(user.getUid()).child("cart");

        } else {
            // No user is signed in
        }

        RecyclerView recyclerView = findViewById(R.id.rvCartItems);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, mColumnCount));
        }
        recyclerView.setAdapter(new MyCartItemRecyclerViewAdapter(myRef, this));
    }

    @Override
    public void onListInteraction(Item mItem) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onListUpdate() {

    }

    public void goToCart(MenuItem item) {
    }
}
