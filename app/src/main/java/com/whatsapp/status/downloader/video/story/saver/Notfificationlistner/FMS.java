package com.whatsapp.status.downloader.video.story.saver.Notfificationlistner;



import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
public class FMS extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {




            new notification(getApplicationContext(), remoteMessage.getData().get("message"),remoteMessage.getData().get("title"));}



   }