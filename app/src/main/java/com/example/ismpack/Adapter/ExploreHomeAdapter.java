package com.example.ismpack.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismpack.Models.ExploreHomeModel;
import com.example.ismpack.R;

import java.util.ArrayList;

public class ExploreHomeAdapter extends RecyclerView.Adapter<ExploreHomeAdapter.viewHolder>{

    ArrayList<ExploreHomeModel> list;
    Context context;

    public ExploreHomeAdapter(ArrayList<ExploreHomeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.explorehome_recyclerview_sample,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ExploreHomeModel model = list.get(position);

        holder.profile.setImageResource(model.getProfile());
        holder.postImage.setImageResource(model.getPostImage());
        holder.save.setImageResource(model.getSave());
        holder.profileNmae.setText(model.getProfileName());
        holder.about.setText(model.getAbout());
        holder.like.setText(model.getLike());
        holder.comment.setText(model.getComment());
        holder.share.setText(model.getShare());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView profile,postImage,save;
        TextView profileNmae,about,like,comment,share;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.explore_userProfile);
            postImage = itemView.findViewById(R.id.explore_post_picture);
            save = itemView.findViewById(R.id.explore_bookmark_icon);
            profileNmae = itemView.findViewById(R.id.explore_userName);
            about = itemView.findViewById(R.id.explore_userAbout);
            like = itemView.findViewById(R.id.explore_post_like);
            comment = itemView.findViewById(R.id.explore_post_comment);
            share = itemView.findViewById(R.id.explore_post_share);


        }
    }

}
