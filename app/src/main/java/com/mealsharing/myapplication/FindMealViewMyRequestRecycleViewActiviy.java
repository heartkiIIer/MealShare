package com.mealsharing.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class FindMealViewMyRequestRecycleViewActiviy extends AppCompatActivity {
    //    database
    DatabaseReference databaseReference;
    //    cardviews
    List<Request> MyRequestsList;
    RecyclerView rv;
    //    Firebase
    private String mUsername;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_meal_view_my_request_recycle_view_activiy);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUsername = mFirebaseUser.getDisplayName();

        // set up recycler view
        rv = findViewById(R.id.FindMealViewMyRequestRecycleView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager((new LinearLayoutManager(this)));
        MyRequestsList = new ArrayList();


        // database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Requests");
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
                                String userName =(String) snap.child("userName").getValue();

                                Request newRequest = new Request();
                                newRequest.setRequestID(nodId);
                                // TODO: 3/1/2020 check thisto check for deleted meal post
                                if (userName.equals(mUsername)){
                                    String mealPostID =(String) snap.child("mealPostID").getValue();
                                    newRequest.setMealPostID(mealPostID);
                                    newRequest.setUserNamefrom((String)snap.child("userNamefrom").getValue());
                                    String locations = (String) snap.child("location").getValue();
                                    newRequest.setLocation(locations);

                                    newRequest.setPhotoURL((String)snap.child("photoURL").getValue());
                                    newRequest.setNumberOfMeals((String)snap.child("numberOfMeals").getValue());

                                    newRequest.setStatus((String)snap.child("status").getValue());
                                    newRequest.setNotes((String)snap.child("notes").getValue());
                                    int new_time=-1;
                                    if (snap.child("minute").getValue()!=null){
                                        Long time=((long)snap.child("minute").getValue());
                                        new_time=time.intValue();
                                    }
                                    newRequest.setMinute(new_time);
                                    if (snap.child("hour").getValue()!=null){
                                        Long time=((long)snap.child("hour").getValue());
                                        new_time=time.intValue();
                                    }
                                    newRequest.setHour(new_time);

                                    MyRequestsList.add(newRequest);
                                }


                            }

                        }

                        // create adapter for recycler view
                        FindMealViewMyRequestAdapter findMealViewMyRequestAdapter = new FindMealViewMyRequestAdapter(MyRequestsList);
                        rv.setAdapter(findMealViewMyRequestAdapter);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }


}
