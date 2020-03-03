package com.mealsharing.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShareMealMyMyPostingsRecycleViewAdapter extends RecyclerView.Adapter<ShareMealMyMyPostingsRecycleViewAdapter.ViewHolder> {

    Context context;
    DatabaseReference databaseReference;
    List<MealSwipes> MyMealSwipesList;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    private int previousPosition = 0;


    public ShareMealMyMyPostingsRecycleViewAdapter(Context ct , List<MealSwipes> TempList, RecyclerViewClickInterface recyclerViewClickInterface) {

        this.MyMealSwipesList = TempList;
         this.context = ct;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_share_meal_my_postings, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final MealSwipes meal = MyMealSwipesList.get(position);

        holder.myposts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShareMealMyPostViewRequestsRecyclerViewActivity.class);

                intent.putExtra("MealPostID", meal.getID());
                Toast.makeText(context,meal.getID(), meal.getID().length()).show();

                context.startActivity(intent);
            }
        });

        holder.myposts_location.setText(meal.getLocations());

        String startTime = Integer.toString(meal.getStartHour());
        startTime=startTime.concat(":");
        startTime=startTime.concat(Integer.toString((meal.getStartMinute())));
        holder.myposts_startTime.setText(startTime);

        String endTime = Integer.toString(meal.getEndHour());
        endTime=endTime.concat(":");
        endTime=endTime.concat(Integer.toString((meal.getEndMinute())));
        holder.myposts_endTime.setText(endTime);

        holder.myposts_notes.setText(meal.getNotes());

        holder.myposts_requestCount.setText(Integer.toString(meal.getRequestCount()));

        final int currentPosition = position;

        previousPosition = position;

        final MealSwipes infoData = MyMealSwipesList.get(position);

        holder.myposts_deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(infoData);
            }
        });

    }

    @Override
    public int getItemCount() {

        return MyMealSwipesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout myposts;
        public TextView myposts_location;
        public TextView myposts_startTime;
        public TextView myposts_endTime;
        public TextView myposts_notes;
        public TextView myposts_requestCount;
        public ImageButton myposts_deleteButton;

        public ViewHolder(View itemView) {

            super(itemView);

            myposts = itemView.findViewById(R.id.myposts);
            myposts_location = (TextView) itemView.findViewById(R.id.myposts_location);
            myposts_requestCount = (TextView) itemView.findViewById(R.id.myposts_requestCount);

            myposts_startTime = (TextView) itemView.findViewById(R.id.myposts_startTime);

            myposts_endTime = (TextView) itemView.findViewById(R.id.myposts_endTime);
            myposts_notes= (TextView) itemView.findViewById(R.id.myposts_notes);

            myposts_deleteButton = (ImageButton) itemView.findViewById(R.id.myposts_deleteButton);

        }

    }


    private void removeItem(MealSwipes infoData) {
        DatabaseReference drMeal = FirebaseDatabase.getInstance().getReference("Meals").child(infoData.getID());
        int currPosition = MyMealSwipesList.indexOf(infoData);
        MyMealSwipesList.remove(currPosition);
        drMeal.removeValue();
        notifyItemRemoved(currPosition);
    }


}
