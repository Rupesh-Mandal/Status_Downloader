package com.whatsapp.status.downloader.video.story.saver.Frags;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.whatsapp.status.downloader.video.story.saver.R;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class frag_dual_loader extends Fragment {
ViewPager viewPager;
TabLayout tabLayout;
RelativeLayout BG;
    AnimationDrawable animationDrawable;
Thread two;
int chktheme;
String workingtype;

    public frag_dual_loader(int chktheme,String workingtype) {
        this.chktheme=chktheme;
        this.workingtype=workingtype;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (chktheme!=R.style.DarkMode){
                   inflater.getContext().setTheme(chktheme);

        }

        View view=inflater.inflate(R.layout.frag_dual_loader, container, false);



        BG = view.findViewById(R.id.bg);


viewPager= view.findViewById(R.id.viewpager);
tabLayout=view.findViewById(R.id.stab);

        if (chktheme==R.style.DarkMode){
            tabLayout.setBackgroundResource(R.drawable.cardbg1);
            tabLayout.setTabTextColors(R.color.clay,Color.WHITE);
            tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        }else {

            tabLayout.setBackgroundResource(R.drawable.cardbg2);
        }

TwoThread();


return  view;




    }


    private void TwoThread() {
        two=  new Thread(new Runnable() {
            @Override
            public void run() {
               getActivity(). runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {


                            FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


                                @Override
                                public int getCount() {
                                    return 2;
                                }

                                @NonNull
                                @Override
                                public Fragment getItem(int position) {
                                    if (position == 0) {

                                        return new ImageFragmenttpx(chktheme, workingtype);
                                    } else if (position == 1) {
                                        return new VideoFragmenttpx(chktheme, workingtype);
                                    }

                                    return null;
                                }

                                @Nullable
                                @Override
                                public CharSequence getPageTitle(int position) {
                                    if (workingtype.equals("DW")) {
                                        if (position == 0) {
                                            return "Saved Photo";
                                        } else if (position == 1) {
                                            return "Saved Video";
                                        }

                                    } else {

                                        if (position == 0) {
                                            return "Photos";
                                        } else if (position == 1) {
                                            return "Videos";
                                        }
                                    }
                                    return null;
                                }
                            };

                            viewPager.setAdapter(adapter);
                            tabLayout.setupWithViewPager(viewPager);
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
        two.start();
    }



}
