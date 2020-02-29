package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindMealOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_meal_options);
        setTitle("Find a Meal");
    }
    public void onFindMealButton(View view) {
        Intent intent = new Intent(this, FindMealActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
    public void onViewRequest(View view) {


    }

}
