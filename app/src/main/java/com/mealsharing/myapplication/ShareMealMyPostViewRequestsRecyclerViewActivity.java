package com.mealsharing.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ShareMealMyPostViewRequestsRecyclerViewActivity extends AppCompatActivity {
    //    database
    DatabaseReference databaseReference;

    //    cardviews
    List<MealSwipes> MyMealSwipesList;
    RecyclerView rv;
    ShareMealMyMyPostingsRecycleViewAdapter adapter; // todo probably will need new adapter
    //    Firebase
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_meal_my_post_view_requests);

        setTitle("Post Details");
    }

}
