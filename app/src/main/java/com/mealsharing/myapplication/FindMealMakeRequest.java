package com.mealsharing.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindMealMakeRequest extends AppCompatActivity {
//    database
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;
    private String mUsername;
    private String mPhotoUrl;

//    widgets
    private Spinner numberMealsRequestedSpinner;
    private TimePicker specificTimePicker;
    private EditText notesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_meal_make_request);

        setTitle("Post Details");
        //         Database stuff
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUsername = mFirebaseUser.getDisplayName();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        if (mFirebaseUser.getPhotoUrl() != null) {
            mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
        }

        // Widgets element ID
        specificTimePicker=  (TimePicker) findViewById(R.id.specificTimePicker);
        numberMealsRequestedSpinner = (Spinner) findViewById(R.id.numberMealsRequestedSpinner);
        notesEditText = (EditText) findViewById(R.id.notesEditText);
        // Spinner click listener
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
        numberMealsRequestedSpinner.setAdapter(dataAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onMakeRequestButton(View view){
        System.out.println("CLICKING MAKE REQUEST");
        Intent intent = getIntent();
        final String mealPostID = intent.getExtras().getString("MealPostID");
        String locationRequest = intent.getExtras().getString("MealPostLocation");
        String userNameto = intent.getExtras().getString("userNameto");

//        create request object
        final Request newRequest= new Request();
        newRequest.setMealPostID(mealPostID);

        // count how many requests have this specific mealPostID
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Meals");
        // Attach a listener to read the data at our posts reference
        Long value = Long.valueOf(0);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Long value = Long.valueOf(0);

                if(dataSnapshot.hasChildren()){
                    Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();
                    while (iter.hasNext()){
                        DataSnapshot snap = iter.next();
                        String nodId = snap.getKey();
                        if(nodId.equals(mealPostID)){
                            value = (long)(snap.child("requestCount").getValue());
                            value += Long.valueOf(1);
                            Log.e("test", String.valueOf(value));
                            FirebaseDatabase.getInstance().getReference().child("Meals").child(mealPostID).child("requestCount").setValue(value);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        String numberMeals="";
        if (numberMealsRequestedSpinner.getSelectedItem()==null){
            numberMeals="1";
        }
        else{
            numberMeals= numberMealsRequestedSpinner.getSelectedItem().toString() ;
        }
        newRequest.setNumberOfMeals(numberMeals);
        newRequest.setHour(specificTimePicker.getHour());
        newRequest.setMinute(specificTimePicker.getMinute());
        newRequest.setNotes(notesEditText.getText().toString());
        newRequest.setUserNamefrom(mUsername);
        newRequest.setUserNameto(userNameto);
        newRequest.setPhotoURL(mPhotoUrl);
        newRequest.setLocation(locationRequest);
//        add to database
        mDatabaseReference.child("Requests").push().setValue(newRequest);

        Toast.makeText(this, "Wish your hunger to end!", Toast.LENGTH_SHORT).show();

    }

}
