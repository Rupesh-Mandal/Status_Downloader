package com.whatsapp.status.downloader.video.story.saver.Model;

import android.graphics.Bitmap;

/**
 * Created by umer on 25-Apr-18.
 */

public class List_Inbox {
    public String Name;
    public String Date;
    public String Message;
    public Bitmap Img;
    boolean New;

    public List_Inbox(String name, String date, String message, Bitmap img) {
        Name = name;
        Date = date;
        Message = message;
        Img = img;
       // this.New=New;
    }



}
