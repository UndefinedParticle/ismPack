package com.example.ismpack.ui.Explore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ismpack.Adapter.ExploreHomeAdapter;
import com.example.ismpack.Models.ExploreHomeModel;
import com.example.ismpack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;


public class ExploreHomeFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseAuth auth;
    RecyclerView exploreHomeRV;
    ArrayList<ExploreHomeModel> exploreHomeList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore_home, container, false);

        exploreHomeRV = view.findViewById(R.id.explore_home_recyclerview);

        exploreHomeList = new ArrayList<>();



        ExploreHomeAdapter exploreHomeAdapter = new ExploreHomeAdapter(exploreHomeList,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        exploreHomeRV.setLayoutManager(layoutManager);
        exploreHomeRV.setNestedScrollingEnabled(false);
        exploreHomeRV.setAdapter(exploreHomeAdapter);

        database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                exploreHomeList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    ExploreHomeModel post = dataSnapshot.getValue(ExploreHomeModel.class);

                    post.setPostId(dataSnapshot.getKey());

                    exploreHomeList.add(post);
                }

                exploreHomeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return  view;
    }
}