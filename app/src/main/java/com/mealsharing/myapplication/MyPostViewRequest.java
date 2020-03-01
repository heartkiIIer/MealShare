package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MyPostViewRequest extends AppCompatActivity {

    //    database
    DatabaseReference databaseReference;

    //    cardviews
    List<MealSwipes> MyMealSwipesList;
    List<Request> MyRequestsList;
    RecyclerView rv;
    MyRequestRecycleViewAdapter adapter;

    //    Firebase
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_meal_my_requesters);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUsername = mFirebaseUser.getDisplayName();

        rv= (RecyclerView)findViewById(R.id.ViewMyRequestRecyclerView);

        //        set layout
        rv.setHasFixedSize(true);
        rv.setLayoutManager((new LinearLayoutManager(this)));
        MyRequestsList=new ArrayList();

        // database stuff here
    }
}
