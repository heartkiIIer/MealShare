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

public class FindMealViewMyRequestAdapter extends RecyclerView.Adapter<FindMealViewMyRequestAdapter.MyViewHolder> {

    Context context;
    List<Request> MyRequestsList;
//    private RecyclerViewClickInterface recyclerViewClickInterface;


    public FindMealViewMyRequestAdapter(List<Request> mealsList){
//        context = ct;
        MyRequestsList = mealsList;
//        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.recycleview_find_meal, parent,false);
//        return new MyViewHolder(view);


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_find_meal_my_requests, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindMealViewMyRequestAdapter.MyViewHolder holder, int position) {

        Request req  = MyRequestsList.get(position);
        holder.myrequests_location.setText(req.getLocation());

        String startTime = Integer.toString(req.getHour());
        startTime=startTime.concat(":");
        startTime=startTime.concat(Integer.toString((req.getMinute())));
        holder.myrequests_time.setText(startTime);

        holder.myrequests_notes.setText(req.getNotes());
        holder.myrequests_status.setText(req.getStatus());


//        holder.myposts_requestCount.setText(Integer.toString(meal.getRequestCount()));

//        holder.myposts_userName.setText(meal.getUserNamefrom());



    }

    // TODO: 3/1/2020 add delete button 
    @Override
    public int getItemCount() {
        return MyRequestsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myrequests_time;
        TextView myrequests_location;
        TextView myrequests_status;
        TextView myrequests_notes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myrequests_time = itemView.findViewById(R.id.myrequests_time);
            myrequests_location = itemView.findViewById(R.id.myrequests_location);
            myrequests_notes = (TextView) itemView.findViewById(R.id.myrequests_notes);
            myrequests_status = (TextView) itemView.findViewById(R.id.myrequests_status);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
//                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

            // on long click

        }
    }



}
