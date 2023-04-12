package com.example.ismpack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ismpack.Adapter.ViewPostAdapter;
import com.example.ismpack.Models.ExploreHomeModel;
import com.example.ismpack.Models.ViewPostModel;
import com.example.ismpack.databinding.ActivityViewPostBinding;
import com.example.ismpack.ui.Explore.ExploreHomeFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewPostActivity extends AppCompatActivity {

    ActivityViewPostBinding binding;

    String post_Id,posted_By;
    Intent intent;

    String posted_by , post_item_name , posted_image , post_like;
    String post_comment_count , post_description , post_comments;
    Intent intent1;

    FirebaseDatabase database;
    FirebaseAuth auth;

    ArrayList<ViewPostModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        intent = getIntent();
        intent1 = getIntent();

        post_Id = intent.getStringExtra("post_Id");
        //posted_By = intent.getStringExtra("posted_By");

        database.getReference().child("Posts").child(post_Id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ExploreHomeModel post = snapshot.getValue(ExploreHomeModel.class);

                Picasso.get().load(post.getPostImage()).placeholder(R.drawable.placeholder).into(binding.viewPostPicture);
                binding.viewPostUserName.setText(post.getPostedBy());
                binding.viewPostUserAbout.setText(post.getPostItemName());
                binding.viewPostDescription.setText(post.getPostDescription());
                binding.viewPostLike.setText(post.getPostLike()+"");
                binding.viewPostComment.setText(post.getCommentCount()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*posted_By = intent1.getStringExtra("posted_By");
        post_item_name = intent1.getStringExtra("post_item_name");
        posted_image = intent1.getStringExtra("posted_image");
        post_like = intent1.getStringExtra("post_like");
        post_comment_count = intent1.getStringExtra("post_comment_count");
        post_description = intent1.getStringExtra("post_description");
        post_comments = intent1.getStringExtra("post_comments");*/


        binding.viewPostSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Date date = new Date();
                ViewPostModel comment = new ViewPostModel();
                comment.setCommentBody(binding.viewPostWriteComment.getText().toString());
                //comment.getCommentedAt(date.getTime());
                comment.setCommentedBy(FirebaseAuth.getInstance().getUid());


                    database.getReference().child("Posts").child(post_Id)
                            .child("comments").push()
                            .setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                                database.getReference().child("Posts").child(post_Id).child("commentCount").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int commentCount = 0;

                                        if(snapshot.exists()){
                                            commentCount = snapshot.getValue(Integer.class);
                                        }

                                        database.getReference().child("Posts").child(post_Id).child("commentCount").setValue(commentCount + 1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                binding.viewPostWriteComment.setText("");
                                                Toast.makeText(ViewPostActivity.this, "Commented", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        }
                    });
            }
        });

        ViewPostAdapter adapter = new ViewPostAdapter(list,this);
        binding.viewPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        database.getReference().child("Posts").child(post_Id).child("comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ViewPostModel comment = dataSnapshot.getValue(ViewPostModel.class);

                    list.add(comment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        binding.viewPostRecyclerView.setAdapter(adapter);
    }
}