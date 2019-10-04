package com.loyality.jsw.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.ActivitiesHistoryActivity;
import com.loyality.jsw.EventDetailActivity;
import com.loyality.jsw.R;
import com.loyality.jsw.serverrequesthandler.models.EventModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyHistoryAdapter extends RecyclerView.Adapter<MyHistoryAdapter.MyViewHolder> {
   private ActivitiesHistoryActivity activitiesHistoryActivity;

   private List<EventModel> eventModels;
    public MyHistoryAdapter(ActivitiesHistoryActivity activitiesHistoryActivity, List<EventModel> eventModelList) {

        this.activitiesHistoryActivity = activitiesHistoryActivity;

        eventModels = eventModelList;
    }

    @NonNull
    @Override
    public MyHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(activitiesHistoryActivity).inflate(R.layout.custom_activity_history,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHistoryAdapter.MyViewHolder holder, int position) {

        EventModel eventModel = eventModels.get(position);
        holder.tvDate.setText(eventModel.getEventDate());
        holder.tvDesc.setText(eventModel.getEventDescription());
        holder.tvName.setText(eventModel.getEventName());
        Picasso.get().load(eventModel.getEventImage()). into(holder.ivImage);

       holder.llRoot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               activitiesHistoryActivity.startActivity(new Intent(activitiesHistoryActivity, EventDetailActivity.class).putExtra("id",eventModel.getId()));
               activitiesHistoryActivity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
           }
       });

    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llRoot;
        AppCompatImageView ivImage;
        AppCompatTextView tvName;
        AppCompatTextView tvDesc;
        AppCompatTextView tvDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llRoot = itemView.findViewById(R.id.llRoot);
             ivImage = itemView.findViewById(R.id.ivImage);
             tvName = itemView.findViewById(R.id.tvActivityName);
             tvDesc = itemView.findViewById(R.id.tvDescription);
             tvDate = itemView.findViewById(R.id.tvActivityDate);

        }
    }

}
