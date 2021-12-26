package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DownloadFile {
    public DownloadFile(Context context, String loc, String getPath, String getTitle, boolean multichk) {






        new FolderChecker(loc,context);

        final File file = new File(getPath);

        File destFile = new File(loc);
        try {
            FileUtils.copyFileToDirectory(file,destFile);


        } catch ( IOException e) {
            e.printStackTrace();
        }

        MediaScannerConnection.scanFile(
                context,
                new String[]{ loc + getTitle},
                new String[]{ "*/*"},
                new MediaScannerConnection.MediaScannerConnectionClient()
                {
                    public void onMediaScannerConnected()
                    {
                    }
                    public void onScanCompleted(String path, Uri uri)
                    {
                        Log.d("path: ",path);
                    }
                });
        if (!multichk) {
            Toast.makeText(context, "Saved !", Toast.LENGTH_LONG).show();

            new adsreq(context);
        }


    }
}
