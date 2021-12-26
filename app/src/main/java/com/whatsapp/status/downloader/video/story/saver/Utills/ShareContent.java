package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.whatsapp.status.downloader.video.story.saver.R;

public class ShareContent  {
    Context context;
    String loc;

    public ShareContent(Context context) {
        this.context = context;
//        loc=context.getSharedPreferences("settings",MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);

    }



    private void WShare(Uri imageUri, String chk){
        Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);
        //Target whatsapp:
        shareIntent.setPackage("com.whatsapp");
        //Add text and then Image URI
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        if (chk.isEmpty()){
        shareIntent.setType("image/*");}else {

            shareIntent.setType("Video/*");
        }
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            context.startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) { }


    }

    public void ImageShare(Uri imageUri) {
        WShare(imageUri, "");
    }
    public void videoShare(Uri imageUri, String chk) {
        WShare(imageUri,chk);
    }




    public void Allapps(Uri imageUri, String chk){
        Allappsimage(imageUri,chk);
    }


    private void Allappsimage(Uri imageUri, String chk){
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (chk.isEmpty()) {
            intent.setType("image/*");
        }else {

            intent.setType("Video/*");

        }
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION| Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent.createChooser(intent, "Share whatsapp status from "+context.getString(R.string.app_name)+" to !");

        try {context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) { }


    }
}
