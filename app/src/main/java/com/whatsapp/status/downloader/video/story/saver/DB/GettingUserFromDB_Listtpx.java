package com.whatsapp.status.downloader.video.story.saver.DB;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;
import com.whatsapp.status.downloader.video.story.saver.Model.Tablename_image;

public class GettingUserFromDB_Listtpx {
Context context;
ArrayList<String> Tablename;
ArrayList<Tablename_image> List_users=new ArrayList<>();
String loc;
    public GettingUserFromDB_Listtpx(Context context) {
        this.context = context;

    }

public ArrayList<Tablename_image> process() {
        // TODO Auto-generated method stub
        loc=  Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_Profile;


       Tablename= new DB_TabNAmetpx(context,"WA").getAllTAbles();

Log.v("tname","tname :  ye ha data get k liye "+Tablename);
        Log.v("msgx", "getting  person image data from db "  );


if (Tablename!=null){
    for (int i=0; i<Tablename.size(); i++){
        // creating a file wd the same name of pic
        File f=new File(loc, Tablename.get(i).replaceAll("\\s+","")+".jpg");
        try {
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            List_users.add(new Tablename_image(Tablename.get(i),b));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}else {

    Log.v("msgx", "person image data from db  empty "  );

}

        return List_users;
        }






}
