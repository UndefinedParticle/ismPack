package com.example.ismpack.ui.Explore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ismpack.Adapter.ViewPagerAdapter;
import com.example.ismpack.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ExploreNotificationFragment extends Fragment {
    private FragmentStateAdapter pagerAdapter;
    ViewPager2 viewPager;
    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_explore_notification, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        //pagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(new ViewPagerAdapter(getActivity()));

        tabLayout = view.findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position){
                        case 0:
                            tab.setText("Notifications");
                            break;
                        case 1:
                            tab.setText("Messages");
                            break;
                    }
                }
        ).attach();

        return view;
    }
}