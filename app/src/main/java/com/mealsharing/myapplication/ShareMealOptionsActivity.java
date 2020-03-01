package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShareMealOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_meal_options);
//        Button ViewPostsButton = findViewById(R.layout.ViewPostsButton);
        setTitle("Share a Meal");

    }

    public void onMakeMealShare(View view) {
        Intent intent = new Intent(this, ShareMealActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
    public void onViewPost(View view) {
        Intent intent = new Intent(this, ShareMealMyPostingsActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);



    }

}
