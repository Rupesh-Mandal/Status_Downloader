package com.whatsapp.status.downloader.video.story.saver.Notfificationlistner;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Random;

import com.whatsapp.status.downloader.video.story.saver.MainActivity;
import com.whatsapp.status.downloader.video.story.saver.Msg_Read_Activity;
import com.whatsapp.status.downloader.video.story.saver.R;

public class notification
{
      Intent intent;
    private final String ADMIN_CHANNEL_ID ="admin_channel";
    public notification(Context context,  String title, String msg, String name, byte[] byteArray) {
        intent = new Intent(context, Msg_Read_Activity.class)
         .putExtra("Name", name)
                .putExtra("Pic", byteArray)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("Notification","Notification");

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = new Random().nextInt(3000);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            stupChnl(notificationManager,title,msg);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.logo);


        int number=0;
        number=number++;

        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder
                (context, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(notificationSoundUri)
                .setContentIntent(pendingIntent);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            notificationBuilder.setColor(context.getResources().getColor(R.color.greendefault));
        }
        notificationManager.notify(notificationID, notificationBuilder.build());



    }
    public notification(Context context, String title, String msg)
    {
            intent = new Intent(context, MainActivity.class);

        intent.putExtra("Notification","Notification");

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = new Random().nextInt(3000);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            stupChnl(notificationManager,title,msg);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.logo);


        int number=0;
        number=number++;

        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder
                (context, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(largeIcon)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(notificationSoundUri)
                .setContentIntent(pendingIntent);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            notificationBuilder.setColor(context.getResources().getColor(R.color.greendefault));
        }
        notificationManager.notify(notificationID, notificationBuilder.build());



    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void stupChnl(NotificationManager notificationManager, String title, String msg)
    {

        NotificationChannel NCH;
        NCH = new NotificationChannel
                (ADMIN_CHANNEL_ID, title, NotificationManager.IMPORTANCE_HIGH);
        NCH.setDescription( "  \n "+msg);
        NCH.enableLights(true);
        NCH.setLightColor(Color.GREEN);
        NCH.enableVibration(true);
        if (notificationManager != null)
        {
            notificationManager.createNotificationChannel(NCH);
        }



    }


}
