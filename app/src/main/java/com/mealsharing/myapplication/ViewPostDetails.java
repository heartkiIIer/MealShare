package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewPostDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post_details);

        setTitle("Post Details");
    }
}
