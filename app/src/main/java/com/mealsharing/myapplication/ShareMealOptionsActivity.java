package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShareMealOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_meal_options);
//        Button ViewPostsButton = findViewById(R.layout.ViewPostsButton);

    }

    public void onMakeMealShare(View view) {
        Intent intent = new Intent(this, ShareMealActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
    public void onViewPost(View view) {
        Intent intent = new Intent(this, ViewMyPostingsActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);



    }

}
