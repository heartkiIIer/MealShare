package com.mealsharing.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ShareMealMyPostViewRequestsRecycleViewAdapter extends RecyclerView.Adapter<ShareMealMyPostViewRequestsRecycleViewAdapter.ViewHolder> {

    Context context;
    List<Request> MyRequestsList;



    public ShareMealMyPostViewRequestsRecycleViewAdapter(List<Request> TempList) {

        this.MyRequestsList = TempList;
        // this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_share_meal_my_requesters, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Request reqs = MyRequestsList.get(position);

//        holder.myposts_location.setText(reqs.getLocation());

        String startTime = Integer.toString(reqs.getHour());
        startTime=startTime.concat(":");
        startTime=startTime.concat(Integer.toString((reqs.getMinute())));
        holder.myposts_startTime.setText(startTime);

//        holder.myposts_notes.setText(reqs.getNotes());

        holder.myposts_requestCount.setText(reqs.getNumberOfMeals());

        holder.myposts_userNamefrom.setText(reqs.getUserNamefrom());

    }

    @Override
    public int getItemCount() {

        return MyRequestsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        public TextView myposts_location;
        public TextView myposts_userNamefrom;
        public TextView myposts_startTime;
//        public TextView myposts_notes;
        public TextView myposts_requestCount;
        public Button myposts_accept;
        public Button myposts_reject;



        public ViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);

            myposts_userNamefrom = itemView.findViewById(R.id.myposts_userNamefrom);

            myposts_requestCount = (TextView) itemView.findViewById(R.id.myposts_requestCount);

            myposts_startTime = (TextView) itemView.findViewById(R.id.myposts_startTime);

//            myposts_notes= (TextView) itemView.findViewById(R.id.myposts_notes);


            myposts_accept = itemView.findViewById(R.id.accept_request);
            myposts_reject = itemView.findViewById(R.id.reject_request);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Clicked item", Toast.LENGTH_SHORT).show();
        }
    }
}