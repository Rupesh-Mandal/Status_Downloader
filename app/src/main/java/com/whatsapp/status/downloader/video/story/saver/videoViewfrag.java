package com.whatsapp.status.downloader.video.story.saver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import com.whatsapp.status.downloader.video.story.saver.Model.WAImageModeltpx;

public class videoViewfrag extends Fragment {
    VideoView videoView;


ImageView play;
    WAImageModeltpx video;
boolean autoplay=false;
    public videoViewfrag(WAImageModeltpx integer, boolean b) {
        video =integer;
        autoplay=b;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sliding_vide_view, container, false);


        play =view.findViewById(R.id.play);
        videoView =view.findViewById(R.id.video);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play.getVisibility()==View.VISIBLE){
                    play.setVisibility(View.GONE);
                    play.performClick();
                }else {
                    play.setVisibility(View.VISIBLE);
                    videoView.pause();
                }
            }
        });

       MediaController mediaController=new MediaController(getActivity());


        videoView.setVideoURI(video.getUri());
        videoView.setMediaController(null);
        mediaController.setAnchorView(videoView);
        setDimension();

play.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        videoView.start();
        play.setVisibility(View.GONE);
    }
});
    return view;

    }

    @Override
    public void onResume() {
//        if (autoplay && videoView!=null){
//
//         play.performClick();
//            play.setVisibility(View.GONE);
//        }

         play.performClick();
        super.onResume();
    }

    @Override
    public void onStop() {
        if (videoView!=null){
            videoView.stopPlayback();

            play.setVisibility(View.VISIBLE);

        }
        super.onStop();

    }

    @Override
    public void onPause() {
        if (videoView.isPlaying()|| videoView!=null){
            videoView.pause();

            play.setVisibility(View.VISIBLE);
        }
        super.onPause();
    }

    private void setDimension() {
        // Adjust the size of the video
        // so it fits on the screen
        float videoProportion = getVideoProportion();
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float screenProportion = (float) screenHeight / (float) screenWidth;
        android.view.ViewGroup.LayoutParams lp = videoView.getLayoutParams();

        if (videoProportion < screenProportion) {
            lp.height= screenHeight;
            lp.width = (int) ((float) screenHeight / videoProportion);
        } else {
            lp.width = screenWidth;
            lp.height = (int) ((float) screenWidth * videoProportion);
        }
        videoView.setLayoutParams(lp);
    }

    private float getVideoProportion()
    {
        return 3f;
    }











    }


