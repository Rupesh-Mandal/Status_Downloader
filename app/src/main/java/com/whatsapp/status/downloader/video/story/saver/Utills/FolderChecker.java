package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class FolderChecker {
    String path;
    Context context;

    public FolderChecker(String path, Context context) {
        this.path = path;
        this.context = context;
        process();
    }

    public FolderChecker(Context context) {
        this.context=context;
    }


    private void process(){
        File dir = new File(path);

        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated)
        {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated)
        {
            Log.d("Folder", "Already Created");
        }
    }


    public File process2(File dir ){

        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated)
        {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated)
        {
            Log.d("Folder", "Already Created");
        }
        return dir;
    }
}
