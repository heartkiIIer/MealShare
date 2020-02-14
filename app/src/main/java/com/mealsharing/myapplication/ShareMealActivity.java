package com.mealsharing.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShareMealActivity extends AppCompatActivity {

//    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference;
    private String userName;
    private String locations;
    private String numberMeals;
    private String userImg ;
    private String fromTime;
    private String toTime;
    private String notes;
    private TimePicker fromTimePicker;
    private TimePicker toTimePicker;
    private EditText notesEditText;
    private Spinner locationsSpinner;
    private Spinner mealsAmount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_meal);
        // Widgets element ID
        mealsAmount = (Spinner) findViewById(R.id.meals_amount);
        locationsSpinner = (Spinner) findViewById(R.id.locationsSpinner);
         fromTimePicker=  (TimePicker) findViewById(R.id.fromTimeTimePicker);
         toTimePicker = (TimePicker) findViewById(R.id.toTimeTimePicker);
         notesEditText = (EditText) findViewById(R.id.ShareMealNoteEditText);


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
        mealsAmount.setAdapter(dataAdapter);
        // Spinner element

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

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }
    /*
    users submit a posting for sharing meals
    insert into database
    */

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onShareMealSubmit(View view){
        String userName="";
        String locations= "";
        if (locationsSpinner.getSelectedItem()==null){
            locations="Campus Center";
        }
        else {
             locations= locationsSpinner.getSelectedItem().toString();
        }
        String numberMeals="";
        if (mealsAmount.getSelectedItem()==null){
            numberMeals="1";
        }
        else{
            numberMeals= mealsAmount.getSelectedItem().toString() ;
        }
//        String userImg="" ;
//        String fromTime= "";
        int startHour= fromTimePicker.getHour();
        int startMinute= fromTimePicker.getMinute();
        int endHour = toTimePicker.getHour();
        int endMinute=toTimePicker.getMinute();
        String notes=notesEditText.getText().toString();

        MealSwipes newMeal= new MealSwipes(userName, locations, numberMeals,userImg, notes,
                startHour, startMinute, endHour, endMinute);
        mDatabaseReference.child("Meals").setValue(newMeal);




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
