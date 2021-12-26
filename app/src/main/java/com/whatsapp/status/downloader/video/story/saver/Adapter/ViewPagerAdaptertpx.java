package com.whatsapp.status.downloader.video.story.saver.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdaptertpx extends FragmentPagerAdapter {

    public ArrayList<Fragment> fragments;
    public ArrayList<String> titles;

    public ViewPagerAdaptertpx(FragmentManager fm)
    {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        this.fragments = new ArrayList<>();
        this.titles = new ArrayList<>();

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles.get(position);
    }


}