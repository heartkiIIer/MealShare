package com.mealsharing.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyPostRecycleViewAdapter extends RecyclerView.Adapter<MyPostRecycleViewAdapter.ViewHolder> {

    Context context;
    List<MealSwipes> MyMealSwipesList;

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




    }

    @Override
    public int getItemCount() {

        return MyMealSwipesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView myposts_location;
        public TextView myposts_startTime;
        public TextView myposts_endTime;
        public TextView myposts_notes;
        public TextView myposts_requestCount;




        public ViewHolder(View itemView) {

            super(itemView);

            myposts_location = (TextView) itemView.findViewById(R.id.myposts_location);
            myposts_requestCount = (TextView) itemView.findViewById(R.id.myposts_requestCount);

            myposts_startTime = (TextView) itemView.findViewById(R.id.myposts_startTime);

            myposts_endTime = (TextView) itemView.findViewById(R.id.myposts_endTime);
            myposts_notes= (TextView) itemView.findViewById(R.id.myposts_notes);
        }
    }
}