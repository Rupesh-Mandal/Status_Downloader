package com.whatsapp.status.downloader.video.story.saver.Notfificationlistner;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Date;

import com.whatsapp.status.downloader.video.story.saver.DB.DBtpx;
import com.whatsapp.status.downloader.video.story.saver.DB.DB_TabNAmetpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAnotifiacationtpx;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationService extends NotificationListenerService {
    StatusBarNotification sbn;
    Context context;
    SharedPreferences AI;
    private String Messeges="";
    private String Name="";
    DBtpx DBtpx;
    Intent intent = new Intent("WAsyNoti");

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {
        String pack="";
        Bitmap bitmap=null;
        int i=0; String date="";
        Bundle msgBundle=null;
        pack = sbn.getPackageName();
        AI=context.getSharedPreferences("AI",MODE_PRIVATE);

               bitmap = sbn.getNotification().largeIcon;
               if (bitmap==null){

                     msgBundle=sbn.getNotification().extras;
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       Icon icon= (Icon) msgBundle.get(Notification.EXTRA_LARGE_ICON);
                       if (icon!=null){
                           Drawable drawable=icon.loadDrawable(context);
                           bitmap=   ((BitmapDrawable)drawable).getBitmap(); }else {
//                           Toast.makeText(this, "icon ni ha", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
        Log.v("msgx","litening");
        if (pack.equals("com.whatsapp")||pack.equals("com.whatsapp.w4b")||pack.equals("com.gbwhatsapp")) {

            Log.v("msgx","in");

            Bundle extrasok = sbn.getNotification().extras;
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)){
                Parcelable[] b = new Parcelable[0];
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    b = (Parcelable[]) extrasok.get(Notification.EXTRA_MESSAGES);
                }

                if(b != null){
                    Messeges = "";
                    for (Parcelable TEMP : b){

                         msgBundle = (Bundle) TEMP;

                        Log.v("msgx", "looping bits: "+i);

if (b.length-1==i) {
    Messeges = msgBundle.getString("text");
    Name = msgBundle.getString("sender");
    date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());

    send("WA",date,Name,Messeges,bitmap);

} i++;




                    }

                }


            }

            Log.v("msgx","msg : "+Messeges+"\n From :"+Name);
        } else {
            return;
        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.v("TABS", "Notification Removed");

    }


    private void  send(String a, String Date, String Name, String msg, Bitmap bitmap) {

        if (!TextUtils.isEmpty(msg)) {
            if (!msg.trim().equals(AI.getString(Name.trim(), "").trim())) {
                if (Messeges.equals("This message was deleted")) {

                    Messeges = "Delete '|_(+_-) _|' Alert! 'above message was deleted recently'";
                    try {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        new notification(context, "Psssst! Delete Alert", "Hmmm it seems someone just deleted something important,\n Note: it could be anything from last hour ", Name, byteArray);

                    } catch (Exception e) {
                    }


                } else if (msg.contains("Voice message (") || msg.contains("Audio (") || msg.contains("Video (")) {
                    Messeges = "Some Media File";
                }


                DBtpx = new DBtpx(context, Name.trim(), "WA", "wa");
                DBtpx.adddata(new WAnotifiacationtpx(Name.trim(), Messeges.trim(), Date));
                Log.v("msgx", a + " : " + Date + " : " + Name + " : " + msg + " pic   : " + bitmap);

                sendBroadcast(intent);

//
//space khatam uper is liye ni kya coz DBtpx mai name chchaeye

                if (Name.contains("+")){
                    Name=Name.replace("+","").trim();
                }
                Name="name"+Name;
Name.replaceAll("\\s+", "");
                AI.edit().putString(Name.trim(), Messeges.trim()).apply();
//                Toast.makeText(context, ""+Messeges, Toast.LENGTH_SHORT).show();

            } else {

                Log.v("msgx", "old msg:-->" + Messeges);

            }


            if (!AI.getBoolean(Name+"img", false)) {

                new saveUserpictpx(context, bitmap, Name, AI);
            }

            if (!AI.getBoolean(Name + "TAB", false)) {

                new DB_TabNAmetpx(context, "WA").addTablesName(Name);

                sendBroadcast(intent);


            }


            DBtpx = null;

        }
    }


}