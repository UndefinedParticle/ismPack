package com.example.ismpack.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ismpack.Fragment.MessageFragment;
import com.example.ismpack.Fragment.NotificationFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new NotificationFragment();
            case 1:return new MessageFragment();
            default:return new NotificationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}


/*


public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new NotificationFragment();
            case 1:return new MessageFragment();
            default:return new NotificationFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position == 0){
            title = "Notification";
        }else if(position == 1){
            title = "Messages";
        }
        return title;
    }


 */