package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.whatsapp.status.downloader.video.story.saver.callbacks.showads;

public class adsreq {
    public adsreq(Context context) {
        try {
            context.sendBroadcast(new Intent(context, showads.class).setAction("com.ads.show"));

        }catch (Exception e){
            Intent intent = new Intent();
            intent.setAction("com.ads.show");
          context.sendBroadcast(intent);

        }
    }
}
