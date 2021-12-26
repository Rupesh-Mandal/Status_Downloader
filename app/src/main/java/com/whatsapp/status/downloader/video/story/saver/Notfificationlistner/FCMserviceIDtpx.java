package com.whatsapp.status.downloader.video.story.saver.Notfificationlistner;


import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class FCMserviceIDtpx extends FirebaseInstanceIdService {

    private static final String SUBSCRIBE_TO = "Tpxsofts";

    @Override
    public void onTokenRefresh() {

        FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
    }
}