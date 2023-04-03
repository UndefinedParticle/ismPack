package com.example.ismpack.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ismpack.Adapter.NotificationAdapter;
import com.example.ismpack.Models.NotificationModel;
import com.example.ismpack.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<NotificationModel> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = view.findViewById(R.id.notification_recyclerView);

        list = new ArrayList<>();

        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));
        list.add(new NotificationModel(R.drawable.sample_profile_picture,"Someone mentioned in your post","Just Now"));


        NotificationAdapter adapter = new NotificationAdapter(list,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }
}