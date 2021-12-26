package com.whatsapp.status.downloader.video.story.saver.Notfificationlistner;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;


public class saveUserpictpx {
    Context context;
    String loc;

    public saveUserpictpx(Context context, Bitmap bitmap, String Name, SharedPreferences AI) {
        this.context = context;

        loc=  Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_Profile;

        saveToInternalStorage(bitmap,Name,AI);
    }

    private void saveToInternalStorage(Bitmap bitmapImage, String name, SharedPreferences AI){


        File mypath=new File(loc ,name.replaceAll("\\s+","").trim().toLowerCase()+".jpg");
        checkFolder(mypath);
        FileOutputStream fos = null;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(mypath);
            if (bitmapImage!=null){
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, stream);}
            else {
                Log.v("msgx"," bitmap empty");

        }
            stream.flush();
                stream.close();

                AI.edit().putBoolean(name + "img", true).apply();

        } catch (FileNotFoundException e) {

            Log.v("msgx", "pic error ______>>>>>"+e);
            e.printStackTrace();
        } catch (IOException e) {


            Log.v("msgx", "pic error ______>>>>>"+e);
            e.printStackTrace();
        }
//        Toast.makeText(context, mypath.getAbsolutePath(), Toast.LENGTH_SHORT).show();
    }

    public void checkFolder(File dir)
    {

        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated)
        {
            isDirectoryCreated = dir.getParentFile().mkdirs();
        }
        if (isDirectoryCreated)
        {
            Log.d("msgx", "pic error: Already Created");
        }
    }
}
