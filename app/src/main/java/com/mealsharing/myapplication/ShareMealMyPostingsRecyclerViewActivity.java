package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

public class ShareMealMyPostingsRecyclerViewActivity extends AppCompatActivity implements RecyclerViewClickInterface{

    // Context
    public Context context = this;

    // RecyclerViewClickInterface context
    final RecyclerViewClickInterface rvContext = this;


    //    database
    DatabaseReference databaseReference;
//    cardviews
    List<MealSwipes> MyMealSwipesList;
    RecyclerView rv;
    ShareMealMyMyPostingsRecycleViewAdapter adapter;
//    Firebase
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_meal_my_postings);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUsername = mFirebaseUser.getDisplayName();

        rv= (RecyclerView)findViewById(R.id.ViewMyPostRecyclerView);

//        set layout
        rv.setHasFixedSize(true);
        rv.setLayoutManager((new LinearLayoutManager(this)));
        MyMealSwipesList=new ArrayList();

//        database
         databaseReference =FirebaseDatabase.getInstance().getReference().child("Meals");
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
//                        Log.i("DA", "dataSnapshot value = "+dataSnapshot.getValue());
                        if(dataSnapshot.hasChildren()){
                            Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();
                            while (iter.hasNext()){
                                DataSnapshot snap = iter.next();
                                String nodId = snap.getKey();
                                MealSwipes newMeal = new MealSwipes();
                                if (nodId != null) {
                                   String userName =(String) snap.child("userName").getValue();
                                   newMeal.setID(nodId);
                                if (userName!=null && userName.equals(mUsername)){
                                    newMeal.setUserName(userName);
                                    String locations = (String) snap.child("locations").getValue();
                                    newMeal.setLocations(locations);

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
//                                        Long time=((long)snap.child("requestCount").getValue());
                                        Long time= (long)(snap.child("requestCount").getValue());
                                        new_time=time.intValue();
                                    }
                                    newMeal.setRequestCount(new_time);

                                    String notes = (String) snap.child("notes").getValue();
                                    newMeal.setNotes(notes);
                                    MyMealSwipesList.add(newMeal);
                                } }


                            }

                        }

                        ShareMealMyMyPostingsRecycleViewAdapter adapter = new ShareMealMyMyPostingsRecycleViewAdapter(context, MyMealSwipesList, rvContext);
                        rv.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }


                });

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "hello", "hello".length());
//        Intent intent = new Intent(this, FindMealRecyclerViewActivity.class);
        Intent intent = new Intent(this, MainActivity.class);
//        Intent intent = new Intent(this, FindMealMakeRequest.class);
//        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

    }

}
