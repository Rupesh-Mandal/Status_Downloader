package com.whatsapp.status.downloader.video.story.saver;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;

import java.io.File;

import com.whatsapp.status.downloader.video.story.saver.Utills.DownloadFile;
import com.whatsapp.status.downloader.video.story.saver.Utills.ShareContent;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;
import com.whatsapp.status.downloader.video.story.saver.Utills.themeapply;

public class VideoViewActivitytpx extends AppCompatActivity implements View.OnClickListener {
    VideoView videoView;
    MediaController mediaController;
    String getPath;
    String loc; Uri getUri;
    String getTitle;
    int chk;
    String actionchk="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          chk=new themeapply().theme(getApplicationContext());
        setTheme(chk);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_view);


        loc=getSharedPreferences("settings",MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);

initailaizing();
 bottomsheetListner();












    }

    private void initailaizing(){

        videoView=findViewById(R.id.video);


        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.fab1).setOnClickListener(this);
        findViewById(R.id.fab2).setOnClickListener(this);
        findViewById(R.id.fab3).setOnClickListener(this);
        findViewById(R.id.fab4).setOnClickListener(this);

        getUri = this.getIntent().getParcelableExtra("uri");
        getPath = this.getIntent().getStringExtra("path");
        getTitle = this.getIntent().getStringExtra("title");
        actionchk = this.getIntent().getStringExtra("actionchk");

        mediaController=new MediaController(this);


        videoView.setVideoURI(getUri);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        setDimension();
        videoView.start();



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




    private void share_Video(String getPath) {
        Uri imageUri = FileProvider.getUriForFile(this, ".fileprovider", new File(getPath));
        new ShareContent(getApplicationContext()).Allapps(imageUri,"video");

    }

    private void shareWhatapp_Video(String path)
    {

        Uri imageUri = FileProvider.getUriForFile(this,
                ".fileprovider", new File(path));
        new ShareContent(getApplicationContext()).videoShare(imageUri,"video");




    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab2) {
            shareWhatapp_Video(getPath);
        } else if (id == R.id.fab3) {
            share_Video(getPath);
        } else if (id == R.id.fab1) {
            new DownloadFile(getApplicationContext(), loc, getPath, getTitle, false);
        } else if (id == R.id.back) {
            finish();
        }else if (id == R.id.fab4) {
            delllImg();
        }
    }

    private void bottomsheetListner(){

        LottieAnimationView pull;
        pull=findViewById(R.id.pull);
        FrameLayout layout_BottomSheet=findViewById(R.id.bottomSheet_media);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(layout_BottomSheet);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    pull.setRotation(180f);

                }
                else  if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    pull.setRotation(0f);

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        pull.setRotation(180f);

        setupbootombf();
    }
    private void setupbootombf() {
        LottieAnimationView pull=findViewById(R.id.pull);
        pull.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(ContextCompat.getColor(getApplicationContext(),new gettingtintColor().gettingtint(chk)) , PorterDuff.Mode.SRC_ATOP));

        ImageView imageView=findViewById(R.id.back);
        findViewById(R.id.c1).setBackgroundResource(new gettingtintColor().gettingtintdrable(chk));
        imageView .setColorFilter(ContextCompat.getColor(getApplicationContext(), new gettingtintColor().gettingtint(chk)), android.graphics.PorterDuff.Mode.SRC_IN);


        if (actionchk.equals("DW")){
            findViewById(R.id.fab4).setVisibility(View.VISIBLE);
            findViewById(R.id.fab4a).setVisibility(View.VISIBLE);
        }

    }
    private  void delllImg() {

        File file = new File(getPath);

        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
