package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FindMealActivity extends AppCompatActivity {

    private static final String TAG = "FINDMEALQUERY";
    private DatabaseReference mDatabaseReference;
    RecyclerView recyclerView;

    String test [] = {"im shine", "im chloe", "im alex", "im hoe"};
    int images [] = {R.drawable.com_facebook_profile_picture_blank_portrait,R.drawable.com_facebook_favicon_blue, R.drawable.common_google_signin_btn_icon_dark, R.drawable.com_facebook_profile_picture_blank_portrait};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_meal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("Meals");
        Log.d(TAG, "trying to debug");

        recyclerView = findViewById(R.id.recycler_view);


        MyAdapter myAdapter = new MyAdapter(this, test, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
