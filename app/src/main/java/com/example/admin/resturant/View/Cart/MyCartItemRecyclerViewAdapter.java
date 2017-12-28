package com.example.admin.resturant.View.Cart;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.resturant.Item;
import com.example.admin.resturant.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MyCartItemRecyclerViewAdapter extends RecyclerView.Adapter<MyCartItemRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RVAdapterTag";
    private final OnListInteractionListener mListener;
    private final DatabaseReference myRef;
    private List<Item> items = new ArrayList<>();
    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Log.d(TAG, "onChildAdded: added");
            //mValues.add(dataSnapshot);
            items.add(dataSnapshot.getValue(Item.class));
            notifyDataSetChanged();
            mListener.onListUpdate();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public MyCartItemRecyclerViewAdapter(DatabaseReference myRef, OnListInteractionListener listener) {
        this.myRef = myRef;
        mListener = listener;
        this.myRef.addChildEventListener(childEventListener);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = items.get(position);

        holder.mNameView.setText(items.get(position).getName());
        holder.mPriceView.setText(items.get(position).getPrice() + "");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mPriceView;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.tvName);
            mPriceView = (TextView) view.findViewById(R.id.tvPrice);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPriceView.getText() + "'";
        }
    }

    interface OnListInteractionListener {
        void onListInteraction(Item mItem);

        void onListUpdate();
    }

}
