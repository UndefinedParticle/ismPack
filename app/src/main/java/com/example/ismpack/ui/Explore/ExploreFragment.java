package com.example.ismpack.ui.Explore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.ismpack.R;
import com.example.ismpack.databinding.FragmentExploreBinding;
import com.example.ismpack.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ExploreFragment extends Fragment {
    private FragmentExploreBinding binding;

    BottomNavigationView bottomNavigationView;

    ExploreHomeFragment exploreHomeFragment = new ExploreHomeFragment();
    ExploreAddFragment exploreAddFragment = new ExploreAddFragment();
    ExploreSearchFragment exploreSearchFragment = new ExploreSearchFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);


        bottomNavigationView = view.findViewById(R.id.bottom_navigation);


        // Set up a listener to switch between other fragments
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exploreHome:
                        // Replace the fragment container with the HomeFragment
                        replaceFragment(new ExploreHomeFragment());
                        return true;
                    case R.id.exploreNotification:
                        // Replace the fragment container with the HomeFragment
                        replaceFragment(new ExploreNotificationFragment());
                        return true;
                    case R.id.exploreAdd:
                        // Replace the fragment container with the AddFragment
                        replaceFragment(new ExploreAddFragment());
                        return true;
                    case R.id.exploreSearch:
                        // Replace the fragment container with the SearchFragment
                        replaceFragment(new ExploreSearchFragment());
                        return true;
                    default:
                        replaceFragment(new ExploreHomeFragment());
                        return true;
                }
            }
        });

        // Set the HomeFragment as the default fragment
        replaceFragment(new ExploreHomeFragment());




        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.explore_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}