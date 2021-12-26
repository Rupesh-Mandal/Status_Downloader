package com.whatsapp.status.downloader.video.story.saver.callbacks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.whatsapp.status.downloader.video.story.saver.MainActivity;

public class showads extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Toast.makeText(context, "ads show rcv", Toast.LENGTH_SHORT).show();
        MainActivity obj = MainActivity.getInstance();
        obj.showadnow();
 }
}
