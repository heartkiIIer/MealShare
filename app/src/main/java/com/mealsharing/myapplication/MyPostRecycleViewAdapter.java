package com.mealsharing.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyPostRecycleViewAdapter extends RecyclerView.Adapter<MyPostRecycleViewAdapter.ViewHolder> {

    DatabaseReference databaseReference;
    Context context;
    List<MealSwipes> MyMealSwipesList;
    // AdapterView.OnItemClickListener mItemClickListener;
    private int previousPosition = 0;

    public MyPostRecycleViewAdapter( List<MealSwipes> TempList) {

        this.MyMealSwipesList = TempList;

//        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_myposts, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MealSwipes meal = MyMealSwipesList.get(position);

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

        public TextView myposts_location;
        public TextView myposts_startTime;
        public TextView myposts_endTime;
        public TextView myposts_notes;
        public TextView myposts_requestCount;
        public ImageButton myposts_deleteButton;

        public ViewHolder(View itemView) {

            super(itemView);

            myposts_location = (TextView) itemView.findViewById(R.id.myposts_location);
            myposts_requestCount = (TextView) itemView.findViewById(R.id.myposts_requestCount);

            myposts_startTime = (TextView) itemView.findViewById(R.id.myposts_startTime);

            myposts_endTime = (TextView) itemView.findViewById(R.id.myposts_endTime);
            myposts_notes= (TextView) itemView.findViewById(R.id.myposts_notes);
            myposts_deleteButton = (ImageButton) itemView.findViewById(R.id.myposts_deleteButton);
           // myposts_deleteButton.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View itemView) {
//            if(itemView.equals(myposts_deleteButton)){
//                removeAt(getAdapterPosition());
//            }else if (mItemClickListener != null) {
//                mItemClickListener.onItemClick(itemView, getAdapterPosition());
//            }
//        }
    }

    private void removeItem(MealSwipes infoData) {
        DatabaseReference drMeal = FirebaseDatabase.getInstance().getReference("Meals").child(infoData.getID());
        int currPosition = MyMealSwipesList.indexOf(infoData);
        MyMealSwipesList.remove(currPosition);
        drMeal.removeValue();
        notifyItemRemoved(currPosition);
    }

//    public void setOnItemClickListener(final AdapterView.OnItemClickListener mItemClickListener) {
//        this.mItemClickListener = mItemClickListener;
//    }
//    public void removeAt(int position) {
//        MyMealSwipesList.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, MyMealSwipesList.size());
//    }

}
