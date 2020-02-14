package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShareMealActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_meal);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.meals_amount);
        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("1");
        categories.add("2 ");
        categories.add("3");
        categories.add("4+");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        // Spinner element
        Spinner locationsSpinner = (Spinner) findViewById(R.id.locationsSpinner);

        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> locationsCategories = new ArrayList<String>();
        locationsCategories.add("Campus Center");
        locationsCategories.add("Daka");
        locationsCategories.add("Library");
        locationsCategories.add("Goats Head");

        // Creating adapter for spinner
        ArrayAdapter<String> locationsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locationsCategories);

        // Drop down layout style - list view with radio button
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        locationsSpinner.setAdapter(locationsAdapter);


    }
    /*
    users submit a posting for sharing meals
    */

    public void onShareMealSubmit(View view){

    }
//    TODO
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void showDatePickerDialog(View view) {
    }
}
