package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAImageModeltpx;
import com.whatsapp.status.downloader.video.story.saver.R;
import com.whatsapp.status.downloader.video.story.saver.imageViewfrag;

import java.io.File;
import java.util.ArrayList;

public class SlidingimageViewActivityreniew extends AppCompatActivity implements View.OnClickListener {

    com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView imageView2;

    String getPath,getTitle;
    String loc;
    int CurrentViewpositiom=0;
    String actionchk="";

    ArrayList<WAImageModeltpx> dataarray=null;
    int currentpos=0;
    ViewPager viewPager;
    int chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         chk=new themeapply().theme(getApplicationContext());
        setTheme(chk);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.slidingimageviewactivity);
        loc=getSharedPreferences("settings",MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);


initialdefin();
        bottomsheetListner();
    }


    private void whatsapp_img_Send(String path)
    {
        Uri imageUri = FileProvider.getUriForFile(this,  ".fileprovider", new File(path));
        new ShareContent(getApplicationContext()).ImageShare(imageUri);


    }


    private void initialdefin(){

        imageView2=findViewById(R.id.img);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.fab1).setOnClickListener(this);
      findViewById(R.id.fab2).setOnClickListener(this);
       findViewById(R.id.fab3).setOnClickListener(this);
        findViewById(R.id.fab4).setOnClickListener(this);

        currentpos=this.getIntent().getIntExtra("Current",0);
        actionchk = this.getIntent().getStringExtra("actionchk");
        getStatusimages(actionchk);

    }



    private void img_Send(String getPath) {
        Uri imageUri = FileProvider.getUriForFile(this, ".fileprovider", new File(getPath));
        new ShareContent(getApplicationContext()).Allapps(imageUri,"");

    }






    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.fab2) {

            getPath=dataarray.get(viewPager.getCurrentItem()).getPath();
            whatsapp_img_Send(getPath);
        } else if (id == R.id.fab3) {

            getPath=dataarray.get(viewPager.getCurrentItem()).getPath();
            img_Send(getPath);
        } else if (id == R.id.fab1) {

            getPath=dataarray.get(viewPager.getCurrentItem()).getPath();
            getTitle=dataarray.get(viewPager.getCurrentItem()).getFilename();
            new DownloadFile(getApplicationContext(), loc, getPath, getTitle, false);
        } else if (id == R.id.back) {
        onBackPressed();
        } else if (id == R.id.fab4) {

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

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED); pull.setRotation(180f);

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
        imageView .setColorFilter(ContextCompat.getColor(getApplicationContext(), new gettingtintColor().gettingtint(chk)), PorterDuff.Mode.SRC_IN);
   if (actionchk.equals("DW")){
       findViewById(R.id.fab4).setVisibility(View.VISIBLE);
       findViewById(R.id.fab4a).setVisibility(View.VISIBLE);
   }




    }

    private  void delllImg() {
getPath=dataarray.get(viewPager.getCurrentItem()).getPath();
        File file = new File(getPath);

        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }



    private void settviedpagerDATA(){
        viewPager= findViewById(R.id.viewpager);

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {


            @Override
            public int getCount() {
                return dataarray.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
Log.v("hassan","current viewpager position : "+CurrentViewpositiom);

                return new imageViewfrag(dataarray.get(position));

            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return null;
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentpos);
    }



    public void getStatusimages(String chks) {
        if (dataarray != null) {
            dataarray.clear();
        }
        if (chks.equals("DW")) {

            dataarray = new GetttingWhatsappFiles(this).saved_imagesprocess(chks);
        } else {
            dataarray = new GetttingWhatsappFiles(this).imagesprocess(chks);

        }
        if (dataarray!=null){
            settviedpagerDATA();
        }else {

            Log.v("hassan"," this is the data recived: array is null");
        }
    }

    @Override
    public void onBackPressed() {

        new adsreq(getApplicationContext());
        finish();
        super.onBackPressed();
    }
    }


