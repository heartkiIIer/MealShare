package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FindMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_meal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Find a Meal");

    }
}
