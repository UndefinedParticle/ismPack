package com.example.ismpack.ui.Explore;

import android.os.Bundle;

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

import java.util.ArrayList;


public class ExploreHomeFragment extends Fragment {

    RecyclerView exploreHomeRV;
    ArrayList<ExploreHomeModel> exploreHomeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore_home, container, false);

        exploreHomeRV = view.findViewById(R.id.explore_home_recyclerview);

        exploreHomeList = new ArrayList<>();
        exploreHomeList.add(new ExploreHomeModel(R.drawable.sample_profile_picture,R.drawable.explore_ism_picture,R.drawable.bookmark,
                "chiNmoy","Living a Life","169","39","9"));
        exploreHomeList.add(new ExploreHomeModel(R.drawable.sample_profile_picture,R.drawable.ism_scpt_team,R.drawable.bookmark,
                "Akshat","IIT(ISM) SCPT Team","69","29","3"));
        exploreHomeList.add(new ExploreHomeModel(R.drawable.sample_profile_picture,R.drawable.ism_gjlt_building,R.drawable.bookmark,
                "Arpit","GJLT Building","139","33","0"));
        exploreHomeList.add(new ExploreHomeModel(R.drawable.sample_profile_picture,R.drawable.ism_library,R.drawable.bookmark,
                "Abhinav","IIT(ISM) Center Library","269","69","19"));
        exploreHomeList.add(new ExploreHomeModel(R.drawable.sample_profile_picture,R.drawable.ism_road,R.drawable.bookmark,
                "Ajit","IIT(ISM) Dhanbad","219","49","19"));
        exploreHomeList.add(new ExploreHomeModel(R.drawable.sample_profile_picture,R.drawable.moon_image,R.drawable.bookmark,
                "Aman","Finding my STAR","196","59","17"));
        exploreHomeList.add(new ExploreHomeModel(R.drawable.sample_profile_picture,R.drawable.leg_injury,R.drawable.bookmark,
                "Ankit","Sad Life","139","79","0"));


        ExploreHomeAdapter exploreHomeAdapter = new ExploreHomeAdapter(exploreHomeList,getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        exploreHomeRV.setLayoutManager(layoutManager);

        exploreHomeRV.setNestedScrollingEnabled(false);
        exploreHomeRV.setAdapter(exploreHomeAdapter);


        return  view;
    }
}