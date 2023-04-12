package com.example.ismpack.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismpack.Models.ExploreHomeModel;
import com.example.ismpack.Models.Users;
import com.example.ismpack.R;
import com.example.ismpack.ViewPostActivity;
import com.example.ismpack.databinding.ExplorehomeRecyclerviewSampleBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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

        Picasso.get().load(model.getPostImage()).placeholder(R.drawable.placeholder).into(holder.binding.explorePostPicture);

        String desc = model.getPostDescription();

        if(desc.equals("")){
            holder.binding.postDescription.setVisibility(View.GONE);
        }else {
            holder.binding.postDescription.setText(model.getPostDescription());
            holder.binding.postDescription.setVisibility(View.VISIBLE);
        }

        holder.binding.explorePostLike.setText(model.getPostLike()+"");
        holder.binding.exploreUserAbout.setText(model.getPostItemName());
        holder.binding.explorePostComment.setText(model.getCommentCount()+"");

        FirebaseDatabase.getInstance().getReference().child("Users").child(model.getPostUserId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                holder.binding.addUserName.setText(user.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPostId())
                .child("likes").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            if(model.getPostLike() != 0){
                                holder.binding.explorePostLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.red_heart, 0, 0, 0);

                                holder.binding.explorePostLike.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPostId())
                                                .child("likes").child(FirebaseAuth.getInstance().getUid())
                                                .setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPostId()).child("postLike")
                                                                .setValue(model.getPostLike() - 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                        holder.binding.explorePostLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.explore_like, 0, 0, 0);
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });
                            }else {
                                holder.binding.explorePostLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.explore_like, 0, 0, 0);

                                holder.binding.explorePostLike.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPostId())
                                                .child("likes").child(FirebaseAuth.getInstance().getUid())
                                                .setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPostId()).child("postLike")
                                                                .setValue(model.getPostLike() + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void unused) {
                                                                        holder.binding.explorePostLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.red_heart, 0, 0, 0);
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });
                            }



                        }else{
                            holder.binding.explorePostLike.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPostId())
                                            .child("likes").child(FirebaseAuth.getInstance().getUid())
                                            .setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPostId()).child("postLike")
                                                            .setValue(model.getPostLike() + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    holder.binding.explorePostLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.red_heart, 0, 0, 0);
                                                                }
                                                            });
                                                }
                                            });
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        holder.binding.explorePostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewPostActivity.class);

                intent.putExtra("post_Id",model.getPostId());
                //intent.putExtra("posted_By",model.getPostedBy());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

            ExplorehomeRecyclerviewSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ExplorehomeRecyclerviewSampleBinding.bind(itemView);




        }
    }

}
