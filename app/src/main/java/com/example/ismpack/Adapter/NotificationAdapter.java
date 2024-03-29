package com.example.ismpack.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismpack.Models.NotificationModel;
import com.example.ismpack.R;


import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewHolder>{

    ArrayList<NotificationModel> list;
    Context context;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(com.example.ismpack.R.layout.notification_recyclerview_sample,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        NotificationModel model = list.get(position);

        holder.profile.setImageResource(model.getProfile());
        holder.notification.setText(model.getNotification());
        holder.time.setText(model.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  viewHolder extends RecyclerView.ViewHolder{

        ImageView profile;
        TextView notification,time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            //profile = itemView.findViewById(com.example.ismpack.R.id.explore_userProfile);
            profile = itemView.findViewById(R.id.notification_profile);
            notification = itemView.findViewById(R.id.notification_mention);
            time = itemView.findViewById(R.id.notification_time);
        }
    }
}
