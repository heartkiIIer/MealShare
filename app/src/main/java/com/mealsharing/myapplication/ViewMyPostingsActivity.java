package com.mealsharing.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewMyPostingsActivity extends AppCompatActivity {
//    database
    DatabaseReference databaseReference;

//    cardviews
    List<MealSwipes> MyMealSwipesList;
    RecyclerView rv;
    MyPostRecycleViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_postings);
        rv= (RecyclerView)findViewById(R.id.ViewMyPostRecyclerView);
//        set layout
        rv.setHasFixedSize(true);
        rv.setLayoutManager((new LinearLayoutManager(this)));
        MyMealSwipesList=new ArrayList();

//        database
         databaseReference =FirebaseDatabase.getInstance().getReference();
         databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Log.d("Tag", "INSIDE DATA CHANGE---:" );
                 System.out.println("INSIDE DATA CHANGE----");
                 if (dataSnapshot.exists()){
                     for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                         MealSwipes meal=npsnapshot.getValue(MealSwipes.class);
                         MyMealSwipesList.add(meal);
                     }
                     adapter=new MyPostRecycleViewAdapter( MyMealSwipesList);
                     rv.setAdapter(adapter);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
    }
//    todo create card view that takes my posting information

}
