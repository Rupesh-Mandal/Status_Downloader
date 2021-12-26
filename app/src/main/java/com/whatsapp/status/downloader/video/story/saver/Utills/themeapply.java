package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;


import com.whatsapp.status.downloader.video.story.saver.R;

import static android.content.Context.MODE_PRIVATE;

public class themeapply {



    public int theme(Context applicationContext) {


        try {

            return applicationContext.getSharedPreferences("Theme",MODE_PRIVATE).getInt("apply", R.style.Default);
        }catch (Exception e){
            return R.style.Default;
        }

    }
}
