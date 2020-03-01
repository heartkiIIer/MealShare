package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class FindMealRecyclerViewActivity extends AppCompatActivity implements RecyclerViewClickInterface{
    //    database
    DatabaseReference databaseReference;
    //    cardviews
    List<MealSwipes> MyMealSwipesList;
    RecyclerView rv;
    //    Firebase
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String userInMap="";

    // todo remove later
    int images [] = {R.drawable.com_facebook_profile_picture_blank_portrait,R.drawable.com_facebook_favicon_blue, R.drawable.common_google_signin_btn_icon_dark, R.drawable.com_facebook_profile_picture_blank_portrait};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_meal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUsername = mFirebaseUser.getDisplayName();

        // set up recycler view
        rv = findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager((new LinearLayoutManager(this)));
        MyMealSwipesList = new ArrayList();

        // context
        final RecyclerViewClickInterface context = this;


//        check if it'click from map
        Intent intent = getIntent();
        try {
            userInMap=intent.getExtras().getString("userInMap");
        }
        catch (Exception e){
            Toast.makeText(this, "this is not from the map", Toast.LENGTH_SHORT).show();
        }
        if (userInMap.isEmpty()){
            Toast.makeText(this, "this is not from the map", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "this is from the map" + userInMap, Toast.LENGTH_SHORT).show();

        }


//        String userInMap = intent.getExtras().getString("userInMap");
//        Toast.makeText(this, "this is from the map", Toast.LENGTH_SHORT).show();

        // database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Meals");
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
//                        Log.i("DA", "dataSnapshot value = "+dataSnapshot.getValue());
                        if(dataSnapshot.hasChildren()){
                            Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();
                                if (userInMap.isEmpty()){
                                    while (iter.hasNext()){
                                        DataSnapshot snap = iter.next();
                                        String nodId = snap.getKey();
                                    String userName =(String) snap.child("userName").getValue();
                                    MealSwipes newMeal = new MealSwipes();
                                    newMeal.setID(nodId);

                                    if (userName!=null){
                                        newMeal.setUserName(userName);
                                        String locations = (String) snap.child("locations").getValue();
                                        newMeal.setLocations(locations);

                                        newMeal.setPhotoURL((String)snap.child("photoURL").getValue());
                                        newMeal.setNumberMeals((String)snap.child("numberMeals").getValue());

                                        int new_time=-1;
                                        if (snap.child("startMinute").getValue()!=null){
                                            Long time=((long)snap.child("startMinute").getValue());
                                            new_time=time.intValue();
                                        }
                                        newMeal.setStartMinute(new_time);
                                        if (snap.child("startHour").getValue()!=null){
                                            Long time=((long)snap.child("startHour").getValue());
                                            new_time=time.intValue();
                                        }
                                        newMeal.setStartHour(new_time);
                                        if (snap.child("endHour").getValue()!=null){
                                            Long time=((long)snap.child("endHour").getValue());
                                            new_time=time.intValue();
                                        }
                                        newMeal.setEndHour(new_time);
                                        if (snap.child("endMinute").getValue()!=null){
                                            Long time=((long)snap.child("endMinute").getValue());
                                            new_time=time.intValue();
                                        }
                                        newMeal.setEndMinute(new_time);
                                        if (snap.child("requestCount").getValue()!=null){
                                            Long time=((long)snap.child("requestCount").getValue());
                                            new_time=time.intValue();
                                        }
                                        newMeal.setRequestCount(new_time);

                                        String notes = (String) snap.child("notes").getValue();
                                        newMeal.setNotes(notes);
                                        MyMealSwipesList.add(newMeal);
                                    }

                                }


                            }
                            else {
                                    while (iter.hasNext()){
                                        DataSnapshot snap = iter.next();
                                        String nodId = snap.getKey();
                                        String userName =(String) snap.child("userName").getValue();
                                        MealSwipes newMeal = new MealSwipes();
                                        newMeal.setID(nodId);

                                        if (userName!=null && userName.equals(userInMap)){
                                            newMeal.setUserName(userName);
                                            String locations = (String) snap.child("locations").getValue();
                                            newMeal.setLocations(locations);

                                            newMeal.setPhotoURL((String)snap.child("photoURL").getValue());
                                            newMeal.setNumberMeals((String)snap.child("numberMeals").getValue());

                                            int new_time=-1;
                                            if (snap.child("startMinute").getValue()!=null){
                                                Long time=((long)snap.child("startMinute").getValue());
                                                new_time=time.intValue();
                                            }
                                            newMeal.setStartMinute(new_time);
                                            if (snap.child("startHour").getValue()!=null){
                                                Long time=((long)snap.child("startHour").getValue());
                                                new_time=time.intValue();
                                            }
                                            newMeal.setStartHour(new_time);
                                            if (snap.child("endHour").getValue()!=null){
                                                Long time=((long)snap.child("endHour").getValue());
                                                new_time=time.intValue();
                                            }
                                            newMeal.setEndHour(new_time);
                                            if (snap.child("endMinute").getValue()!=null){
                                                Long time=((long)snap.child("endMinute").getValue());
                                                new_time=time.intValue();
                                            }
                                            newMeal.setEndMinute(new_time);
                                            if (snap.child("requestCount").getValue()!=null){
                                                Long time=((long)snap.child("requestCount").getValue());
                                                new_time=time.intValue();
                                            }
                                            newMeal.setRequestCount(new_time);

                                            String notes = (String) snap.child("notes").getValue();
                                            newMeal.setNotes(notes);
                                            MyMealSwipesList.add(newMeal);
                                        }

                                    }


                            }

                        }


                        // create adapter for recycler view
                        FindMealRecycleViewAdapter findMealRecycleViewAdapter = new FindMealRecycleViewAdapter(MyMealSwipesList, context);
                        rv.setAdapter(findMealRecycleViewAdapter);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    @Override
    public void onItemClick(int position){
        Toast.makeText(this,"hello", "hello".length()).show();
        System.out.println(position);
        MealSwipes current = MyMealSwipesList.get(position);
        String name=current.getUserName();
        System.out.println(name);
        Intent intent = new Intent(this, FindMealMakeRequest.class);
        intent.putExtra("MealPostID", current.getID());
        intent.putExtra("MealPostLocation", current.getLocations());

        startActivity(intent);

    }
    @Override
    public void onLongItemClick(int position){
        // do nothing
    }
}
