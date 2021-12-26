package com.whatsapp.status.downloader.video.story.saver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.whatsapp.status.downloader.video.story.saver.Frags.Direct_messaagetpx;
import com.whatsapp.status.downloader.video.story.saver.Frags.ImageFragmenttpx;
import com.whatsapp.status.downloader.video.story.saver.Frags.MSGFragment;
import com.whatsapp.status.downloader.video.story.saver.Frags.VideoFragmenttpx;
import com.whatsapp.status.downloader.video.story.saver.Frags.frag_dual_loader;
import com.whatsapp.status.downloader.video.story.saver.Frags.frag_inapp;
import com.whatsapp.status.downloader.video.story.saver.Utills.Constants;
import com.whatsapp.status.downloader.video.story.saver.Utills.adsreq;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;
import com.whatsapp.status.downloader.video.story.saver.Utills.themeapply;

import java.util.List;
import java.util.Objects;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PurchasesUpdatedListener {
    CardView WA, BWA, GBWA;
    ImageView Waimg,Wavid,Waall;
    ImageView BWaimg,BWavid,BWaall;
    ImageView GBWaimg,GBWavid,GBWaall;
    LinearLayout eror;
    LottieAnimationView pull;
    FrameLayout layout_BottomSheet;
    RadioButton WAR, BWAR, GBWAR;
    SharedPreferences.Editor def;
    int chk;
    boolean home=false,suscribed=false,minimizedx=false;
    private static MainActivity instance;

    String pkg ="";
    BottomSheetBehavior bottomSheetBehavior;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chk = new themeapply().theme(getApplicationContext());
        setTheme(chk);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (chk==R.style.DarkMode){
            CoordinatorLayout coordinatorLayout=findViewById(R.id.bg);
            coordinatorLayout.setBackgroundColor(Color.parseColor("#121212"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.lightblk));
            }
            carddarkmode();
        }

        purchasechk();
        def = getSharedPreferences("stts", MODE_PRIVATE).edit();
        bottomsheetListner();
        intiatialsteps();
        setupbootombf();
        fcm();
        rendomrateus();

        Interstitial_add_load();
    }

    @Override
    protected void onStart() { // onStart() of your activity
        super.onStart();
        instance = this;
    }
        private void bottomsheetListner() {

        layout_BottomSheet = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layout_BottomSheet);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    pull.setRotation(180f);

                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    pull.setRotation(0f);

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


    }

    @Override
    protected void onResume() {
        
        super.onResume();
        if (!interstitialAd.isLoaded()){

            Interstitial_add_load();
        }
        if (home && minimizedx){
            Log.v("min","min:  "+minimizedx+",,"+home);

            clearfrags();
            selectwhatsapp();

            minimizedx=false;
        }
        suscribed = getSharedPreferences("ads", Context.MODE_PRIVATE).getBoolean("suscribed", false);



    }

    private void intiatialsteps() {
        pkg =  getPackageName();
        WAR = findViewById(R.id.WAR);
        WAR.setOnClickListener(this);
        BWAR = findViewById(R.id.BWAR);
        BWAR.setOnClickListener(this);
        GBWAR = findViewById(R.id.GBWAR);
        GBWAR.setOnClickListener(this);
        pull = findViewById(R.id.pull);
        WA = findViewById(R.id.one);
        BWA = findViewById(R.id.two);
        GBWA = findViewById(R.id.three);
        eror = findViewById(R.id.eror);

        findViewById(R.id.WAST).setOnClickListener(this);
        findViewById(R.id.BWAST).setOnClickListener(this);


        findViewById(R.id.share).setOnClickListener(this);
        findViewById(R.id.sharel).setOnClickListener(this);
        findViewById(R.id.gotohome).setOnClickListener(this);
        findViewById(R.id.rate).setOnClickListener(this);
        findViewById(R.id.ratel).setOnClickListener(this);
        findViewById(R.id.GBWAST).setOnClickListener(this);
        findViewById(R.id.pur).setOnClickListener(this);
        findViewById(R.id.purt).setOnClickListener(this);


         Waimg=findViewById(R.id.WAP);
         Wavid=findViewById(R.id.WAV);
         Waall=findViewById(R.id.WAS);


        BWaimg=findViewById(R.id.BWAP);
        BWavid=findViewById(R.id.BWAV);
        BWaall=findViewById(R.id.BWAS);



        GBWaimg=findViewById(R.id.GBWAP);
       GBWavid=findViewById(R.id.GBWAV);
        GBWaall=findViewById(R.id.GBWAS);
        waCHECKER();
        setuprad();
        selectwhatsapp();
    }

    private void waCHECKER() {

        if (appInstalledOrNot("com.whatsapp")) {
            WA.setVisibility(View.VISIBLE);
counter++;

        }
        if (appInstalledOrNot("com.whatsapp.w4b")) {

            BWA.setVisibility(View.VISIBLE);
            counter++;

        }
        if (appInstalledOrNot("com.gbwhatsapp")) {
            GBWA.setVisibility(View.VISIBLE);
counter++;

        }


        if (counter==0) {
            eror.setVisibility(View.VISIBLE);
        }


    }


    private boolean appInstalledOrNot(String uri) {

        PackageManager chkr = null;
        boolean app_installed;
        try {
            chkr = getPackageManager();
            chkr.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    boolean clicked = false;

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.share:
            case R.id.sharel:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                String text = "Hey have a look at this awesome app \n it contains all type features for status downloading \n so click the link to install it for free";
                String link = "https://play.google.com/store/apps/details?id="+pkg;
                i.putExtra(Intent.EXTRA_TEXT, text + " " + link);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(i, "Thanks for Sharing Status Downloader").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));


                break;
            case R.id.rate:
            case R.id.ratel:
                int ID = getApplicationInfo().labelRes;
                String pstorelnk = "https://play.google.com/store/apps/details?id="+pkg;

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + pkg))
                            .putExtra(Intent.EXTRA_SUBJECT,getString(ID)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } catch (ActivityNotFoundException w) {startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pstorelnk + pkg)));
                }
                break;



            case R.id.menue:
                if (clicked) {

                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    clicked = false;
                } else {
                    // not clicked
                    clicked = true;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.thmt:
            case R.id.thmc:clsmenue();
                startActivity(new Intent(this, ThemeActivity.class));

                break;
                case R.id.sms:
            case R.id.smsg:
                home=false;
                clearfrags();
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new Direct_messaagetpx(chk)).commit();
                clsmenue();
                break;
            case R.id.dell:
            case R.id.dellg:
                home=false;
                clearfrags();
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new MSGFragment(chk)).commit();
                clsmenue();
                break;

            //////WA
            case R.id.WAP:
            case R.id.WAPT:

                Log.v("testingerror","wa");
                home=false;
                clearfrags();
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new ImageFragmenttpx(chk, "WA")).commit();
                WAR();
                clsmenue();

                break;
            case R.id.WAV:
            case R.id.WAVT:
                home=false;

                clearfrags();
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new VideoFragmenttpx(chk, "WA")).commit();
                WAR();
                clsmenue();
                break;
            case R.id.downloa:

                home=false;
                clearfrags();
                WAR();

                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new frag_dual_loader(chk, "DW")).commit();
                clsmenue();
                break;

            case R.id.WAS:
            case R.id.WAST:

                home=true;
                clearfrags();
                WAR();

                Log.v("testingerror","dual");
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new frag_dual_loader(chk, "WA")).commit();

                clsmenue();
                break;
            ///BWA
            case R.id.BWAP:
            case R.id.BWAPT:
                home=false;
                clearfrags();

                Log.v("testingerror","Bwa");
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new ImageFragmenttpx(chk, "BWA")).commit();
                BWAR();
                clsmenue();
                break;
            case R.id.BWAV:
            case R.id.BWAVT:
                home=false;

                clearfrags();
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new VideoFragmenttpx(chk, "BWA")).commit();
                BWAR();
                clsmenue();
                break;
            case R.id.BWAS:
            case R.id.BWAST:
                home=true;
                clearfrags();
                BWAR();

                Log.v("testingerror","BWA dual");
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new frag_dual_loader(chk, "BWA")).commit();
                clsmenue();
                break;
            ///GBWA
            case R.id.GBWAP:
            case R.id.GBWAPT:
                home=false;
                clearfrags();

                Log.v("testingerror","GB");
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new ImageFragmenttpx(chk, "GBWA")).commit();
                GBWAR();
                clsmenue();
                break;
            case R.id.GBWAV:
            case R.id.GBWAVT:

                home=false;
                clearfrags();
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new VideoFragmenttpx(chk, "GBWA")).commit();
                GBWAR();
                clsmenue();
                break;
            case R.id.GBWAS:
            case R.id.GBWAST:
                home=true;

                clearfrags();

                Log.v("testingerror","GBdual");
                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new frag_dual_loader(chk, "GBWA")).commit();
                GBWAR();
                clsmenue();
                break;
                case R.id.pur:
            case R.id.purt:

                home=false;
                clearfrags();

                getSupportFragmentManager().beginTransaction().add(R.id.containerx,
                        new frag_inapp( chk,suscribed)).commit();

                clsmenue();
                break;

            case R.id.WAR:
                WAR();

                findViewById(R.id.WAST).performClick();
                break;
            case R.id.BWAR:
                BWAR();
                findViewById(R.id.BWAST).performClick();
                break;
            case R.id.GBWAR:
                GBWAR();
                findViewById(R.id.GBWAST).performClick();
                break;
            case R.id.gotohome:

                clearfrags();
                selectwhatsapp();
                break;
        }
    }

    private void setuprad() {

        Log.v("sts","mode is empty: "+getSharedPreferences("stts", MODE_PRIVATE).getString("def", ""));
if (counter!=0) {
    if (getSharedPreferences("stts", MODE_PRIVATE).getString("def", "").equals("")) {
        //simplw wa not installed
        Log.v("sts", "mode:  app exists" + getSharedPreferences("stts", MODE_PRIVATE).getString("def", ""));

        if (appInstalledOrNot("com.whatsapp")) {

            def.putString("def", "WA").apply();
        } else if (appInstalledOrNot("com.whatsapp.w4b")) {
            def.putString("def", "BWA").apply();

        } else if (appInstalledOrNot("com.gbwhatsapp")) {
            def.putString("def", "GBWA").apply();


        }
    }

}
    }
private void  selectwhatsapp(){


    switch (getSharedPreferences("stts", MODE_PRIVATE).getString("def", "")) {
        case "WA":
            Log.v("frags", "def:  WA");
            WAR();
            findViewById(R.id.WAST).performClick();
            break;
        case "BWA":

            Log.v("frags", "def:  BWA");
            BWAR();
            findViewById(R.id.BWAST).performClick();
            break;
        case "GBWA":
            Log.v("frags", "def:  GBWA");
            GBWAR();
            findViewById(R.id.GBWAST).performClick();
            break;
    }
}

    private void WAR() {

        BWAR.setChecked(false);
        GBWAR.setChecked(false);
        WAR.setChecked(true);

        def.putString("def", "WA").apply();
    }

    private void BWAR() {
        WAR.setChecked(false);
        GBWAR.setChecked(false);
        BWAR.setChecked(true);
        def.putString("def", "BWA").apply();
    }

    private void GBWAR() {
        BWAR.setChecked(false);
        WAR.setChecked(false);
        GBWAR.setChecked(true);
        def.putString("def", "GBWA").apply();
    }

    private void clearfrags() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    private void setupbootombf() {
        pull.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                frameInfo -> new PorterDuffColorFilter(ContextCompat.getColor(getApplicationContext(), new gettingtintColor().gettingtint(chk)), PorterDuff.Mode.SRC_ATOP));


        findViewById(R.id.three1).setBackgroundResource(new gettingtintColor().gettingtint(chk));
        findViewById(R.id.four).setBackgroundResource(new gettingtintColor().gettingtint(chk));
        findViewById(R.id.five1).setBackgroundResource(new gettingtintColor().gettingtint(chk));
        findViewById(R.id.six).setBackgroundResource(new gettingtintColor().gettingtint(chk));

        findViewById(R.id.eight1).setBackgroundResource(new gettingtintColor().gettingtint(chk));
        findViewById(R.id.eight2).setBackgroundResource(new gettingtintColor().gettingtint(chk));
        findViewById(R.id.c3).setBackgroundResource(new gettingtintColor().gettingtintdrable(chk));
        findViewById(R.id.c2).setBackgroundResource(new gettingtintColor().gettingtintdrable(chk));
        findViewById(R.id.c1).setBackgroundResource(new gettingtintColor().gettingtintdrable(chk));



        ImageViewCompat.setImageTintList(Waimg, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));
        ImageViewCompat.setImageTintList(Wavid, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));
        ImageViewCompat.setImageTintList(Waall, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));


        ImageViewCompat.setImageTintList(BWaimg, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));
        ImageViewCompat.setImageTintList(BWavid, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));
        ImageViewCompat.setImageTintList(BWaall, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));



        ImageViewCompat.setImageTintList(GBWaimg, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));
        ImageViewCompat.setImageTintList(GBWavid, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));
        ImageViewCompat.setImageTintList(GBWaall, ColorStateList.valueOf(ContextCompat.getColor(this, new gettingtintColor().gettingtint(chk))));




    }

private void clsmenue(){

    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    if (suscribed==false){
//    Interstitial_add_load();
    }else {
//        Toast.makeText(this, ""+suscribed, Toast.LENGTH_SHORT).show();
    }
}


    private void fcm() {


        FirebaseMessaging.getInstance().subscribeToTopic("Tpxsofts");
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
//                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

                FirebaseMessaging.getInstance().subscribeToTopic("Tpxsofts");
            }
        });
    }

    InterstitialAd interstitialAd = null;
int i=0;

    public void Interstitial_add_load() {

//            if (i >= 3) {
//                i = 0;
//            } else if (i == 0) {
//                i++;




            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {

                }
            });

            if (interstitialAd == null) {
                interstitialAd = new InterstitialAd(this);
                interstitialAd.setAdUnitId(getString(R.string.ADDIS));
            }
            interstitialAd.loadAd(new AdRequest.Builder().build());

            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                }

                @Override
                public void onAdFailedToLoad(LoadAdError error) {
                    // Gets the domain from which the error came.
                    Log.v("hassan", "main add int :----> " + error.toString());

                    int errorCode = error.getCode();
                    if (errorCode == 3) {
                        Interstitial_add_load();

                    }
                    // All of this information is available via the error's toString() method.
                       }

            });

//            }
//            if (i != 0) {
//                i++;
//            }

            Log.v("ADS", "ADS count: " + i);


    }
int count=0;
    @Override
    public void onBackPressed() {
count++;
if (home){


    new AlertDialog.Builder(this)
            .setIcon(R.drawable.logo)
            .setTitle("Exit!")
            .setMessage("Are you sure you want to Exit")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }

            })
            .setNegativeButton("No", null)
            .show();
}else {
    clearfrags();
    selectwhatsapp();
}
    }

    private void rendomrateus(){
        AppRate.with(this)

                .setInstallDays(1)

                .setLaunchTimes(3)

                .setRemindInterval(1)
                .monitor();

        AppRate
                .showRateDialogIfMeetsConditions(
                        this);
    }
    private void carddarkmode(){

        CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c14,c15,c16,c17,c18,c19,c20,c21,c22;
        c1=findViewById(R.id.one);
        c2=findViewById(R.id.two);
        c3=findViewById(R.id.three);
        c4=findViewById(R.id.c11);
        c5=findViewById(R.id.c10);
        c6=findViewById(R.id.c13);
        c7=findViewById(R.id.c4);
        c8=findViewById(R.id.c5);
        c9=findViewById(R.id.c6);
        c14=findViewById(R.id.c14);
        c15=findViewById(R.id.c15);
        c16=findViewById(R.id.c16);
        c17=findViewById(R.id.c17);
        c18=findViewById(R.id.c18);
        c19=findViewById(R.id.c19);
        c20=findViewById(R.id.c20);
        c21=findViewById(R.id.c21);
        c22=findViewById(R.id.c22);

        c1.setCardBackgroundColor(Color.parseColor("#121212"));
        c2.setCardBackgroundColor(Color.parseColor("#121212"));
        c3.setCardBackgroundColor(Color.parseColor("#121212"));
        c4.setCardBackgroundColor(Color.parseColor("#121212"));
        c5.setCardBackgroundColor(Color.parseColor("#121212"));
        c6.setCardBackgroundColor(Color.parseColor("#121212"));
        c7.setCardBackgroundColor(Color.parseColor("#121212"));
        c8.setCardBackgroundColor(Color.parseColor("#121212"));
        c9.setCardBackgroundColor(Color.parseColor("#121212"));
        c14.setCardBackgroundColor(Color.parseColor("#121212"));
        c15.setCardBackgroundColor(Color.parseColor("#121212"));
        c16.setCardBackgroundColor(Color.parseColor("#121212"));
        c17.setCardBackgroundColor(Color.parseColor("#121212"));
        c18.setCardBackgroundColor(Color.parseColor("#121212"));
        c19.setCardBackgroundColor(Color.parseColor("#121212"));
        c20.setCardBackgroundColor(Color.parseColor("#121212"));
        c21.setCardBackgroundColor(Color.parseColor("#121212"));
        c22.setCardBackgroundColor(Color.parseColor("#121212"));


    }


    public void purchasechk() {
        try {
            suscribed=   getSharedPreferences("ads", Context.MODE_PRIVATE).getBoolean("suscribed",false);

            if (suscribed) {
                 Constants.SUSCRIBED=suscribed;
            } else {

                BillingClient billingClient = BillingClient.newBuilder(getApplicationContext()).enablePendingPurchases().setListener(this).build();
                billingClient.startConnection(new BillingClientStateListener() {
                    @Override
                    public void onBillingSetupFinished(BillingResult billingResult) {
                        Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.SUBS);

                        billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.SUBS, (billingResult1, list) -> {

                            Log.d("billingprocess", "purchasesResult.getPurchasesList():" + purchasesResult.getPurchasesList());

                            if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK &&
                                    !Objects.requireNonNull(purchasesResult.getPurchasesList()).isEmpty()) {
                                Constants.SUSCRIBED = true;
                                suscribed = true;
                                getSharedPreferences("ads", Context.MODE_PRIVATE).edit().putBoolean("suscribed",true).apply();
                                   Log.v("bill", " status bill : " + suscribed);
                            } else {

                                Log.v("bill", " status bill : " + suscribed);
                            }
                        });
                    }

                    @Override
                    public void onBillingServiceDisconnected() {
                        try {

                            purchasechk();
                        } catch (Exception e) {

                        }
                        // Try to restart the connection on the next request to
                        // Google Play by calling the startConnection() method.
                        Log.d("bill", "onBillingServiceDisconnected");
                    }

                });
                billingClient.endConnection();
            } }catch(Exception e){
                Log.v("bill", " exception : " + e);
            }
        }


        @Override
        public void onPurchasesUpdated (@NonNull BillingResult
        billingResult, @Nullable List < Purchase > list){

        }

    @Override
    protected void onStop() {
        super.onStop();
        minimizedx=true;
    }

    public static MainActivity getInstance(){
        return instance;
    }
    public void showadnow() {
        if (suscribed) {
            Constants.SUSCRIBED = suscribed;
        } else {
        //show

          if (interstitialAd.isLoaded()){

                interstitialAd.show();

              Log.v("hassan", "Showing ads");
            }else {
//              Toast.makeText(instance, "no ad availible", Toast.LENGTH_SHORT).show();
              ifAdmiss();
          }

        }
    }

    public void ifAdmiss() {



        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        if (interstitialAd == null) {
            interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId(getString(R.string.ADDIS));
        }
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError error) {
                // Gets the domain from which the error came.
                Log.v("hassan", "main add int :----> " + error.toString());

                int errorCode = error.getCode();
                if (errorCode == 3) {
                    ifAdmiss();

                }
                // All of this information is available via the error's toString() method.
            }

        });


        Log.v("ADS", "ADS count: " + i);


    }}