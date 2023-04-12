package com.example.ismpack.ui.Explore;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class ExploreSearchFragment extends Fragment {
    FragmentExploreSearchBinding binding;
    ArrayList<SearchModel> list;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    RecyclerView exploreSearchRV;
    MenuItem menuItem;
    SearchAdapter adapter;

    SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

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

        //adapter = new SearchAdapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        exploreSearchRV.setLayoutManager(layoutManager);
        exploreSearchRV.setItemAnimator(null);
        //exploreSearchRV.setNestedScrollingEnabled(false);
        //exploreSearchRV.setAdapter(adapter);

        FirebaseRecyclerOptions<SearchModel> options = new FirebaseRecyclerOptions.Builder<SearchModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("Posts"), SearchModel.class)
                .build();

        /*database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
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
        });*/

        adapter = new SearchAdapter(options,getContext());
        exploreSearchRV.setAdapter(adapter);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        menuItem = menu.findItem(R.id.search_bar);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    public void processSearch(String s){

        FirebaseRecyclerOptions<SearchModel> options = new FirebaseRecyclerOptions.Builder<SearchModel>().setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("Posts").orderByChild("postItemName").startAt(s).endAt(s + "\uf8ff"), SearchModel.class)
                .build();

        adapter = new SearchAdapter(options,getContext());
        adapter.startListening();
        exploreSearchRV.setAdapter(adapter);

    }
}