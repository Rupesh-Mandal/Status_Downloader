package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MultisendWhatsapp {
    String packg;
    Context context;

    public MultisendWhatsapp(Context context) {
        this.context = context;
        waCHECKER();
    }

    private void waCHECKER() {
String chk=context.getSharedPreferences("stts", MODE_PRIVATE).getString("def", "WA");
if ( chk.equals("WA")) {

packg="com.whatsapp";

        }else if ( chk.equals("BWA")) {
    packg="com.whatsapp.w4b";
        }else if ( chk.equals("GBWA")) {
    packg="com.gbwhatsapp";
}


    }
    public void multiimagesend(ArrayList<Uri> multifiles){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Here are some files.");
        intent.setType("*/*");
        intent.setPackage(packg);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, multifiles);
        context.startActivity(Intent.createChooser(intent, "Status downloader"));

    }
}
