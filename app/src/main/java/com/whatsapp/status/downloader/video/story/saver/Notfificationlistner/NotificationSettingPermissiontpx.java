package com.whatsapp.status.downloader.video.story.saver.Notfificationlistner;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

public class NotificationSettingPermissiontpx {
    Context context;

    public NotificationSettingPermissiontpx(Context context) {
        this.context = context;

    }
    public boolean process(){
        try {
            String enabledListeners = Settings.Secure.getString(context.getContentResolver(),
                    "enabled_notification_listeners");
            if (!enabledListeners.contains("com.whatsapp.status.downloader.video.story.saver.Notfificationlistner.NotificationService")) {
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
                context.startActivity(intent);

            }else {
                return true ;
            }
            }catch (Exception e){
            Log.v("x",e.toString());

        }  if (! NotificationManagerCompat.from(context).areNotificationsEnabled()){
                Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS" ) ;
                context.startActivity(intent);

            }else {

            return true ;
        }
return false;

    }

    public boolean status(){
        try {
            String enabledListeners = Settings.Secure.getString(context.getContentResolver(),
                    "enabled_notification_listeners");
            return enabledListeners.contains("com.whatsapp.status.downloader.video.story.saver.Notfificationlistner.NotificationService");
        }catch (Exception e){
            Log.v("x",e.toString());

        }
        return NotificationManagerCompat.from(context).areNotificationsEnabled();

    }



    }





