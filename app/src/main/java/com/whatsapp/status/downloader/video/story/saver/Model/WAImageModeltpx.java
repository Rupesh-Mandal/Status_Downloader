package com.whatsapp.status.downloader.video.story.saver.Model;

import android.net.Uri;

/**
 * Created by umer on 25-Apr-18.
 */

public class WAImageModeltpx {
    private final String path;
    private final String filename;
    private final Uri uri;
    private boolean isSelected = false;

    public WAImageModeltpx(String path, String filename, Uri uri)
    {
        this.path = path;
        this.filename=filename;
        this.uri=uri;
    }



    public String getPath() {
        return path;
    }




    public String getFilename()
        {
        return filename;
    }


    public Uri getUri() {
        return uri;
    }







    public void setSelected(boolean selected) {
        isSelected = selected;
    }



    public boolean isSelected() {
        return isSelected;
    }

}
