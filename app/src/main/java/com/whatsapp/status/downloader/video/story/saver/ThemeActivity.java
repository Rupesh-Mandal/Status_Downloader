package com.whatsapp.status.downloader.video.story.saver;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.whatsapp.status.downloader.video.story.saver.R;

import com.whatsapp.status.downloader.video.story.saver.Utills.Constants;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;
import com.whatsapp.status.downloader.video.story.saver.Utills.themeapply;


public class ThemeActivity extends AppCompatActivity implements Switch.OnCheckedChangeListener {
Switch darkmode,defaultmode,purlpemode,redmode,yellowmode,greenmode,skymode,coffemode,whitemode;
int i=0;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        int chk=new themeapply().theme(getApplicationContext());
        setTheme(chk);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);




        darkmode=findViewById(R.id.dark_mode);
        darkmode.setOnCheckedChangeListener(this);

        whitemode=findViewById(R.id.white_mode);
        whitemode.setOnCheckedChangeListener(this);

        defaultmode=findViewById(R.id.default_mode);
        defaultmode.setOnCheckedChangeListener(this);


        purlpemode=findViewById(R.id.purple_mode);
        purlpemode.setOnCheckedChangeListener(this);


     redmode=findViewById(R.id.red_mode);
     redmode.setOnCheckedChangeListener(this);

        yellowmode=findViewById(R.id.yellow_mode);
        yellowmode.setOnCheckedChangeListener(this);


        greenmode=findViewById(R.id.green_mode);
        greenmode.setOnCheckedChangeListener(this);



        skymode=findViewById(R.id.sky_mode);
        skymode.setOnCheckedChangeListener(this);



        coffemode=findViewById(R.id.coffe_mode);
        coffemode.setOnCheckedChangeListener(this);

   chk(new themeapply().theme(getApplicationContext()));
        if (chk==R.style.DarkMode){
            RelativeLayout relativeLayout=findViewById(R.id.bg);
            relativeLayout.setBackgroundColor(Color.parseColor("#121212"));

        }

        findViewById(R.id.back).setBackgroundResource(new gettingtintColor().gettingtint(chk));


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



//            coffemode.setVisibility(View.GONE);
//            skymode.setVisibility(View.GONE);
//            darkmode.setVisibility(View.GONE);
//            yellowmode.setVisibility(View.GONE);
//            redmode.setVisibility(View.GONE);
//            purlpemode.setVisibility(View.GONE);


    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){


            case R.id.green_mode:
                if (b){
                    darkmode.setChecked(false);
                    redmode.setChecked(false);
                    purlpemode.setChecked(false);
                    defaultmode.setChecked(false);
                    yellowmode.setChecked(false);
                    coffemode.setChecked(false);
                    skymode.setChecked(false);
                    whitemode.setChecked(false);

                    greenmode.setChecked(true);
                    getSharedPreferences("Theme",MODE_PRIVATE).edit().putInt("apply",R.style.Green).commit();

                    i++;     applying();

                }

                break;

             case R.id.dark_mode:
                if (b){
                    if (Constants.SUSCRIBED){
                    darkmode.setChecked(true);
                    redmode.setChecked(false);
                    purlpemode.setChecked(false);
                    defaultmode.setChecked(false);
                    yellowmode.setChecked(false);
                    greenmode.setChecked(false);
                    coffemode.setChecked(false);
                    skymode.setChecked(false);
                        whitemode.setChecked(false);

                    getSharedPreferences("Theme",MODE_PRIVATE).edit().putInt("apply",R.style.DarkMode).commit();

                    i++;     applying();}else {
                  notice(R.id.dark_mode);
}

                }

                break;
            case R.id.default_mode:
                if (b) {
                    //orange
                    defaultmode.setChecked(true);
                    darkmode.setChecked(false);
                    redmode.setChecked(false);
                    purlpemode.setChecked(false);
                    yellowmode.setChecked(false);
                    greenmode.setChecked(false);
                    whitemode.setChecked(false);
                    coffemode.setChecked(false);
                    skymode.setChecked(false);

                    getSharedPreferences("Theme", MODE_PRIVATE).edit().putInt("apply", R.style.Default).commit();

                    i++;     applying(); }
                break;
            case R.id.purple_mode:
                if (b) {
                    if (Constants.SUSCRIBED){
                    getSharedPreferences("Theme", MODE_PRIVATE).edit().putInt("apply", R.style.PurpleMode).commit();
                    purlpemode.setChecked(true);
                    defaultmode.setChecked(false);
                    redmode.setChecked(false);
                    darkmode.setChecked(false);
                    yellowmode.setChecked(false);
                        whitemode.setChecked(false);
                    greenmode.setChecked(false);
                    coffemode.setChecked(false);
                    skymode.setChecked(false);
                    i++;     applying();}else {
               notice(R.id.purple_mode);  }

        }
                break;

            case R.id.red_mode:

                if (b) {

                    if (Constants.SUSCRIBED){
                    getSharedPreferences("Theme", MODE_PRIVATE).edit().putInt("apply", R.style.LightRed).commit();
                    redmode.setChecked(true);
                    darkmode.setChecked(false);
                    purlpemode.setChecked(false);
                    defaultmode.setChecked(false);
                        whitemode.setChecked(false);
                    greenmode.setChecked(false);
                    yellowmode.setChecked(false);
                    coffemode.setChecked(false);
                    skymode.setChecked(false);
                    i++;applying();}else {
                        notice(R.id.red_mode);    }

        }
                break;


            case R.id.yellow_mode:

                if (b) {
        if (Constants.SUSCRIBED){

                    getSharedPreferences("Theme", MODE_PRIVATE).edit().putInt("apply", R.style.yelloMode).commit();
                    yellowmode.setChecked(true);
                    redmode.setChecked(false);
                    darkmode.setChecked(false);
                    purlpemode.setChecked(false);
                    defaultmode.setChecked(false);
                    greenmode.setChecked(false);
            whitemode.setChecked(false);
                    coffemode.setChecked(false);
                    skymode.setChecked(false);
                    i++; applying();}else {
            notice(R.id.yellow_mode);        }

        }
                break;
            case R.id.sky_mode:

                if (b) {
                    if (Constants.SUSCRIBED){


                        getSharedPreferences("Theme", MODE_PRIVATE).edit().putInt("apply", R.style.Sky).commit();
                    yellowmode.setChecked(false);
                    redmode.setChecked(false);
                        whitemode.setChecked(false);
                    darkmode.setChecked(false);
                    purlpemode.setChecked(false);
                    defaultmode.setChecked(false);
                    greenmode.setChecked(false);
                    coffemode.setChecked(false);
                    skymode.setChecked(true);
                    i++; applying();}else {
                        notice(R.id.sky_mode);      }

                }
                break;
            case R.id.coffe_mode:

                if (b) {    if (Constants.SUSCRIBED){


                    getSharedPreferences("Theme", MODE_PRIVATE).edit().putInt("apply", R.style.coffe).commit();
                    yellowmode.setChecked(false);
                    redmode.setChecked(false);
                    darkmode.setChecked(false);
                    purlpemode.setChecked(false);
                    defaultmode.setChecked(false);
                    greenmode.setChecked(false);
                    whitemode.setChecked(false);
                    coffemode.setChecked(true);
                    skymode.setChecked(false);
                    i++; applying();}else {
                    notice(R.id.coffe_mode);      }

                }
                break;
            case R.id.white_mode:

                if (b) {


                    getSharedPreferences("Theme", MODE_PRIVATE).edit().putInt("apply", R.style.whitex).commit();
                    yellowmode.setChecked(false);
                    redmode.setChecked(false);
                    darkmode.setChecked(false);
                    purlpemode.setChecked(false);
                    defaultmode.setChecked(false);
                    greenmode.setChecked(false);
                    whitemode.setChecked(true);
                    coffemode.setChecked(false);
                    skymode.setChecked(false);
                    i++; applying();}


                break;

        }
    }



   public void chk(int i){

        if (i==R.style.Default){defaultmode.setChecked(true);
            redmode.setChecked(false);
            purlpemode.setChecked(false);
            yellowmode.setChecked(false);
            darkmode.setChecked(false);
            greenmode.setChecked(false);
            coffemode.setChecked(false);
            skymode.setChecked(false);
            whitemode.setChecked(false);

        }else

       if (i==R.style.DarkMode){   darkmode.setChecked(true);
           redmode.setChecked(false);
           purlpemode.setChecked(false);
           defaultmode.setChecked(false);
           yellowmode.setChecked(false);
           greenmode.setChecked(false);
           coffemode.setChecked(false);
           skymode.setChecked(false);
           whitemode.setChecked(false);

       }else

       if (i==R.style.PurpleMode){       purlpemode.setChecked(true);
           defaultmode.setChecked(false);
           redmode.setChecked(false);
           darkmode.setChecked(false);
           whitemode.setChecked(false);
           yellowmode.setChecked(false);
           greenmode.setChecked(false);
           coffemode.setChecked(false);
           skymode.setChecked(false);

       }else

       if (i==R.style.LightRed){    redmode.setChecked(true);
           purlpemode.setChecked(false);
           purlpemode.setChecked(false);
           defaultmode.setChecked(false);
           yellowmode.setChecked(false);
           greenmode.setChecked(false);
           coffemode.setChecked(false);
           whitemode.setChecked(false);
           skymode.setChecked(false);

       }else

       if (i==R.style.yelloMode){   yellowmode.setChecked(true);
           redmode.setChecked(false);
           purlpemode.setChecked(false);
           purlpemode.setChecked(false);
           defaultmode.setChecked(false);
           greenmode.setChecked(false);
           coffemode.setChecked(false);
           whitemode.setChecked(false);
           skymode.setChecked(false);

       }
       else

       if (i==R.style.Green){   greenmode.setChecked(true);
           redmode.setChecked(false);
           purlpemode.setChecked(false);
           purlpemode.setChecked(false);
           defaultmode.setChecked(false);
           yellowmode.setChecked(false);
           coffemode.setChecked(false);
           whitemode.setChecked(false);
           skymode.setChecked(false);

       }       else

       if (i==R.style.Sky){   greenmode.setChecked(false);
           redmode.setChecked(false);
           purlpemode.setChecked(false);
           purlpemode.setChecked(false);
           defaultmode.setChecked(false);
           yellowmode.setChecked(false);
           coffemode.setChecked(false);
           skymode.setChecked(true);
           whitemode.setChecked(false);

       }   else

       if (i==R.style.coffe){   greenmode.setChecked(false);
           redmode.setChecked(false);
           purlpemode.setChecked(false);
           purlpemode.setChecked(false);
           defaultmode.setChecked(false);
           yellowmode.setChecked(false);
           coffemode.setChecked(true);
           whitemode.setChecked(false);
           skymode.setChecked(false);

       }
       else

       if (i==R.style.whitex){   greenmode.setChecked(false);
           redmode.setChecked(false);
           purlpemode.setChecked(false);
           purlpemode.setChecked(false);
           defaultmode.setChecked(false);
           yellowmode.setChecked(false);
           coffemode.setChecked(false);
           whitemode.setChecked(true);
           skymode.setChecked(false);

       }




   }











    public void applying()
    {
        if (i>1){
        startActivity(new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        i=0;
    }}

private void notice(int chk){
    new AlertDialog.Builder(this)
            .setIcon(R.drawable.logo)
            .setTitle("   Premium Themes!")
            .setMessage("Theme available only for premium users")
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                 if (R.id.dark_mode==chk){

                     darkmode.setChecked(false);
                 }
                 if (chk==R.id.purple_mode){

                     purlpemode.setChecked(false);
                 }else
                 if (chk==R.id.red_mode){

                     redmode.setChecked(false);
                 }else
                 if (chk==R.id.yellow_mode
                 ){

                     yellowmode.setChecked(false);
                 }else
                 if (chk==R.id.coffe_mode){

                     coffemode.setChecked(false);
                 }else
                    if (chk==R.id.sky_mode){

                        skymode.setChecked(false);
                    }



                    dialog.dismiss();
                }

            })
            .show();
}

}