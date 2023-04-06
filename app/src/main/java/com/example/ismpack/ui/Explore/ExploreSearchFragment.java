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

import com.example.ismpack.Adapter.SearchAdapter;
import com.example.ismpack.Models.ExploreHomeModel;
import com.example.ismpack.Models.SearchModel;
import com.example.ismpack.Models.Users;
import com.example.ismpack.R;
import com.example.ismpack.databinding.FragmentExploreSearchBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import java.util.ArrayList;


public class ExploreSearchFragment extends Fragment {
    FragmentExploreSearchBinding binding;
    ArrayList<SearchModel> list;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    RecyclerView exploreSearchRV;

    SearchAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore_search, container, false);

        exploreSearchRV = view.findViewById(R.id.explore_search_recyclerview);
        list = new ArrayList<>();

        adapter = new SearchAdapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        exploreSearchRV.setLayoutManager(layoutManager);
        exploreSearchRV.setNestedScrollingEnabled(false);
        exploreSearchRV.setAdapter(adapter);

        /*FirebaseRecyclerOptions<SearchModel> options = new FirebaseRecyclerOptions.Builder<SearchModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), SearchModel.class)
                .build();*/

        database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    SearchModel post = dataSnapshot.getValue(SearchModel.class);

                    list.add(post);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        exploreSearchRV.setAdapter(adapter);


        return view;
    }

    
}