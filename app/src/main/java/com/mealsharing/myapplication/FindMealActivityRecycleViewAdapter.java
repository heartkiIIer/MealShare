package com.mealsharing.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FindMealActivityRecycleViewAdapter extends RecyclerView.Adapter<FindMealActivityRecycleViewAdapter.MyViewHolder> {

    Context context;
    List<MealSwipes> MyMealSwipesList;
    private RecyclerViewClickInterface recyclerViewClickInterface;


    public FindMealActivityRecycleViewAdapter(List<MealSwipes> mealsList, RecyclerViewClickInterface recyclerViewClickInterface){
//        context = ct;
        MyMealSwipesList = mealsList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.recycleview_find_meal, parent,false);
//        return new MyViewHolder(view);


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_find_meal, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindMealActivityRecycleViewAdapter.MyViewHolder holder, int position) {

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

//        holder.myposts_requestCount.setText(Integer.toString(meal.getRequestCount()));

        holder.myposts_userName.setText(meal.getUserName());

        if (meal.getPhotoURL() != null){
            String imageUrl = meal.getPhotoURL();
            Glide.with(holder.myposts_userImage.getContext()).load(imageUrl).into(holder.myposts_userImage);
        }

    }

    @Override
    public int getItemCount() {
        return MyMealSwipesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myposts_userName;
        TextView myposts_location;
        TextView myposts_startTime;
        TextView myposts_endTime;
        TextView myposts_notes;
        TextView myposts_requestCount;
        ImageView myposts_userImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myposts_userName = itemView.findViewById(R.id.myposts_userName);
            myposts_userImage = itemView.findViewById(R.id.myposts_userImage);
            myposts_location = (TextView) itemView.findViewById(R.id.myposts_location);
            myposts_requestCount = (TextView) itemView.findViewById(R.id.myposts_requestCount);
            myposts_startTime = (TextView) itemView.findViewById(R.id.myposts_startTime);
            myposts_endTime = (TextView) itemView.findViewById(R.id.myposts_endTime);
            myposts_notes= (TextView) itemView.findViewById(R.id.myposts_notes);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

            // on long click

        }
    }



}
