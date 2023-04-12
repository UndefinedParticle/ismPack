package com.example.ismpack.Adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismpack.Models.Users;
import com.example.ismpack.Models.ViewPostModel;
import com.example.ismpack.R;
import com.example.ismpack.databinding.CommentSampleBinding;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPostAdapter extends RecyclerView.Adapter<ViewPostAdapter.viewHolder>{

    ArrayList<ViewPostModel> list;
    Context context;

    public ViewPostAdapter(ArrayList<ViewPostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_sample,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ViewPostModel comment = list.get(position);

        String time = TimeAgo.using(comment.getCommentedAt());
        //holder.binding.commentTime.setText(time);
        holder.binding.userComment.setText(comment.getCommentBody());
        //holder.binding.commentTime.setText(comment.getCommentedAt()+"");

        FirebaseDatabase.getInstance().getReference().child("Users").child(comment.getCommentedBy()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);

                holder.binding.userComment.setText(Html.fromHtml("<b>" +  users.getUserName() + "</b>" + "  " + comment.getCommentBody()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        CommentSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CommentSampleBinding.bind(itemView);
        }
    }

}
