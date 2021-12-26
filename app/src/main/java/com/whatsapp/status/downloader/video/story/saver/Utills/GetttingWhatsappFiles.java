package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;


import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAImageModeltpx;

import static android.content.Context.MODE_PRIVATE;

public class GetttingWhatsappFiles {
    Context context;
    File[] Lost_of_file_to_be_read;
    ArrayList<WAImageModeltpx> arrayList = new ArrayList<>();
String loc;
    public GetttingWhatsappFiles(Context context) {
        this.context = context;
    }
    public ArrayList<WAImageModeltpx> imagesprocess(String chks) {
        Lost_of_file_to_be_read=null;
        arrayList.clear();


        Log.v("frags","CHk rcv :--->"+chks);

        if (chks.equals("BWA")){

            Log.v("frags","CHk rcv :---> in BWA");
            Lost_of_file_to_be_read = new File(new StringBuffer().append(Environment.getExternalStorageDirectory().getAbsolutePath()+Constants.BWA).toString()).listFiles();
        }else if (chks.equals("WA")){

            Log.v("frags","CHk rcv :---> in WA");
            Lost_of_file_to_be_read = new File(new StringBuffer().append(Environment.getExternalStorageDirectory().getAbsolutePath()+Constants.WA).toString()).listFiles();

        }else if (chks.equals("GBWA")){

            Log.v("frags","CHk rcv :---> in GBWA");
            Lost_of_file_to_be_read = new File(new StringBuffer().append(Environment.getExternalStorageDirectory().getAbsolutePath()+Constants.GBWA).toString()).listFiles();


        }

        if (Lost_of_file_to_be_read != null && Lost_of_file_to_be_read.length >= 1) {
            Arrays.sort(Lost_of_file_to_be_read, LastModifiedFileComparator.LASTMODIFIED_REVERSE);

            Log.v("frags","CHk Lost_of_file_to_be_read :--->"+Lost_of_file_to_be_read);

            for (File file : Lost_of_file_to_be_read) {
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".png")) {

                    Log.v("frags","CHk file :---> "+file.getName());
                    arrayList.add(new WAImageModeltpx(file.getAbsolutePath(), file.getName(), Uri.fromFile(file)));
                }
            }


        }
        if (arrayList.isEmpty()) {
            Log.v("frags","CHk array empty :--->"+arrayList.size());
            return null;

        }else {

            Log.v("frags","CHk array not empty :--->"+arrayList.size());
            return arrayList;
        }
    }
    public ArrayList<WAImageModeltpx> saved_imagesprocess(String chks) {
        loc=context.getSharedPreferences("settings",MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);

        Lost_of_file_to_be_read=null;
        arrayList.clear();


            Lost_of_file_to_be_read = new File(new StringBuffer().append(loc).toString()).listFiles();




        if (Lost_of_file_to_be_read != null && Lost_of_file_to_be_read.length >= 1) {
            Arrays.sort(Lost_of_file_to_be_read, LastModifiedFileComparator.LASTMODIFIED_REVERSE);

            Log.v("frags","CHk Lost_of_file_to_be_read :--->"+Lost_of_file_to_be_read);

            for (File file : Lost_of_file_to_be_read) {
                if (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".png")) {

                    Log.v("frags", "CHk file :---> " + file.getName());
                    arrayList.add(new WAImageModeltpx(file.getAbsolutePath(), file.getName(), Uri.fromFile(file)));
                }
            }


        }
        if (arrayList.isEmpty()) {
            Log.v("frags","CHk array empty :--->"+arrayList.size());
            return null;

        }else {

            Log.v("frags","CHk array not empty :--->"+arrayList.size());
            return arrayList;
        }
    }

    public ArrayList<WAImageModeltpx> videosprocess(String chks) {
        Lost_of_file_to_be_read=null;
        arrayList.clear();





        if (chks.equals("BWA")){
            Lost_of_file_to_be_read = new File(new StringBuffer().append(Environment.getExternalStorageDirectory().getAbsolutePath()+Constants.BWA).toString()).listFiles();
        }else if (chks.equals("WA")){
            Lost_of_file_to_be_read = new File(new StringBuffer().append(Environment.getExternalStorageDirectory().getAbsolutePath()+Constants.WA).toString()).listFiles();

        }else if (chks.equals("GBWA")){
            Lost_of_file_to_be_read = new File(new StringBuffer().append(Environment.getExternalStorageDirectory().getAbsolutePath()+Constants.GBWA).toString()).listFiles();


        }

        if (Lost_of_file_to_be_read != null && Lost_of_file_to_be_read.length >= 1) {
            Arrays.sort(Lost_of_file_to_be_read, LastModifiedFileComparator.LASTMODIFIED_REVERSE);

            for (File file : Lost_of_file_to_be_read) {
                if (file.getName().endsWith(".mp4") || file.getName().endsWith(".avi") || file.getName().endsWith(".mkv") || file.getName().endsWith(".gif")) {
                    arrayList.add(new WAImageModeltpx(file.getAbsolutePath(), file.getName(), Uri.fromFile(file)));
                }
            }


        }
        if (arrayList.isEmpty()) {
            return null;
        }else {

            return arrayList;
        }
    }
    public ArrayList<WAImageModeltpx> saved_videosprocess() {
        loc=context.getSharedPreferences("settings",MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);

        Lost_of_file_to_be_read=null;
        arrayList.clear();





            Lost_of_file_to_be_read = new File(new StringBuffer().append(loc).toString()).listFiles();


        if (Lost_of_file_to_be_read != null && Lost_of_file_to_be_read.length >= 1) {
            Arrays.sort(Lost_of_file_to_be_read, LastModifiedFileComparator.LASTMODIFIED_REVERSE);



            for (File file : Lost_of_file_to_be_read) {
                if (file.getName().endsWith(".mp4") || file.getName().endsWith(".avi") || file.getName().endsWith(".mkv") || file.getName().endsWith(".gif")) {
                    arrayList.add(new WAImageModeltpx(file.getAbsolutePath(), file.getName(), Uri.fromFile(file)));
                }

            }


        }
        if (arrayList.isEmpty()) {
            return null;
        }else {

            return arrayList;
        }
    }






    }
