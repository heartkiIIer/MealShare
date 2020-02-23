package com.mealsharing.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.util.JsonToken.NULL;

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
         databaseReference =FirebaseDatabase.getInstance().getReference().child("Meals");
        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
//                        collectPhoneNumbers((Map<String,Object>) dataSnapshot.getValue());
//                        System.out.println(dataSnapshot.getValue());
//                        System.out.println(dataSnapshot.getValue(MealSwipes.class));
//                        MealSwipes meal= dataSnapshot.getValue(MealSwipes.class);
//                        MyMealSwipesList.add(meal);
                        Log.i("DA", "dataSnapshot value = "+dataSnapshot.getValue());
                        if(dataSnapshot.hasChildren()){
                            Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();
                            while (iter.hasNext()){
                                DataSnapshot snap = iter.next();
                                String nodId = snap.getKey();
                                String locations = (String) snap.child("locations").getValue();
                                int new_time=-1;
                                if (snap.child("startHour").getValue()!=null){
                                    Long time=((long)snap.child("startHour").getValue());
                                     new_time=time.intValue();
                                }

                                System.out.println(locations);
                                System.out.println("STARTTIME" + new_time);
                                String notes = (String) snap.child("notes").getValue();
                                System.out.println("INSIDE---------------------------------");
                                MealSwipes newMeal = new MealSwipes();
                                newMeal.setLocations(locations);
                                newMeal.setStartHour(new_time);
                                newMeal.setNotes(notes);
                                MyMealSwipesList.add(newMeal);

                                //received results
//                                Log.i("DA", username + " on nod " + nodId);
                            }

                        }
                        adapter=new MyPostRecycleViewAdapter( MyMealSwipesList);
                        rv.setAdapter(adapter);
                        System.out.println(("--------------------"));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
                ;

//        for (Object meal: MyMealSwipesList){
//            MealSwipes new_meal=(MealSwipes)meal;
//
//        }
//         databaseReference.add
//         databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//             @Override
//             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                 Log.d("Tag", "INSIDE DATA CHANGE---:" );
//                 System.out.println("INSIDE DATA CHANGE----");
//                 if (dataSnapshot.exists()){
//                     for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
//                         MealSwipes meal=npsnapshot.getValue(MealSwipes.class);
//                         System.out.println(meal);
//                         MyMealSwipesList.add(meal);
//
//                     }
//                     adapter=new MyPostRecycleViewAdapter( MyMealSwipesList);
//
//                     rv.setAdapter(adapter);
//                 }
//             }
//
//             @Override
//             public void onCancelled(@NonNull DatabaseError databaseError) {
//
//             }
//         });
    }
    private void collectPhoneNumbers(Map<String,Object> users) {

//        ArrayList<MealSwipes> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Log.d("tag", "ERORR WHY FUCK: "+entry.getValue());
            System.out.println(entry.getValue());
//            Map singleUser = (Map) entry.getValue();
//            //Get phone field and append to list
//            MealSwipes meal = new MealSwipes();
//            meal.setStartHour((int)(long)singleUser.get("startHour"));
//            meal.setLocations((String)singleUser.get("locations"));
//            phoneNumbers.add((Long) singleUser.get("phone"));
        }

//        System.out.println(phoneNumbers.toString());
    }

//    todo create card view that takes my posting information

}
