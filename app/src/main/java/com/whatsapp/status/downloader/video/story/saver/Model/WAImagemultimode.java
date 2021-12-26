package com.whatsapp.status.downloader.video.story.saver.Model;

import android.net.Uri;

/**
 * Created by umer on 25-Apr-18.
 */

public class WAImagemultimode {
    private final String path;
    private final String filename;
    private final Uri uri;

    private final Uri SPuriuri;
    private boolean isSelected = false;

    public WAImagemultimode(String path, String filename, Uri uri, Uri sPuriuri)
    {
        this.path = path;
        this.filename=filename;
        this.uri=uri;
        SPuriuri = sPuriuri;
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
