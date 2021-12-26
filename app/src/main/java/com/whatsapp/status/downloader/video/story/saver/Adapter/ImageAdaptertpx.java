package com.whatsapp.status.downloader.video.story.saver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Frags.ImageFragmenttpx;
import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAImageModeltpx;
import com.whatsapp.status.downloader.video.story.saver.R;
import com.whatsapp.status.downloader.video.story.saver.Utills.FolderChecker;
import com.whatsapp.status.downloader.video.story.saver.Utills.SlidingimageViewActivityreniew;
import com.whatsapp.status.downloader.video.story.saver.Utills.adsreq;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;
import com.whatsapp.status.downloader.video.story.saver.callbacks.multiselect;

import static android.content.Context.MODE_PRIVATE;

public class ImageAdaptertpx extends RecyclerView.Adapter<ImageAdaptertpx.ProgHolder> {

    boolean multicheck=false;
    ArrayList<WAImageModeltpx> multiselecteditems =new ArrayList<>();
    Context context;
    ArrayList<WAImageModeltpx> filesList;
    String loc,actionchk="";
    int currentTheme;
    multiselect multiselectx;

    Vibrator vib ;
    public ImageAdaptertpx(Context context, ImageFragmenttpx imageFragmenttpx, ArrayList<WAImageModeltpx> filesList, int chk, String type_of_whatsapp_chk)
    {
        this.context = context;
        this.filesList = filesList;
        currentTheme=chk;
        multiselectx= imageFragmenttpx;
        loc=context.getSharedPreferences("settings",MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);
        actionchk=type_of_whatsapp_chk;
    }



    @NonNull
    @Override
    public ProgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_item_images,parent,false);
        return new ProgHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProgHolder holder, final int position)
    {

        if (!TextUtils.isEmpty(actionchk)){
            if (actionchk.equals("DW")){
                holder.images_download.setImageResource(R.drawable.ic_baseline_delete_forever_24);
            }
        }

        ImageViewCompat.setImageTintList(holder.images_download, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));
         Glide.with(context).load(filesList.get(position).getUri()).into(holder.imageView);









    }

    @Override
    public int getItemCount()

    {
        return filesList.size();
    }





    public class ProgHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView imageView;
        ImageView images_download;


        public ProgHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_custom);
            images_download =itemView.findViewById(R.id.images_download);

            imageView.setOnClickListener(this);
            images_download.setOnClickListener(this);
            imageView.setOnLongClickListener(this);
            vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        }
        @Override
        public void onClick(View v) {
            if (!multicheck) {


                if (v.getId() == R.id.images_download) {
                    if (!TextUtils.isEmpty(actionchk)) {
                        if (actionchk.equals("DW")) {
                            dellfile(filesList.get(getAdapterPosition()).getPath(), getAdapterPosition());
                        } else {


                            loc = context.getSharedPreferences("settings", MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);

                            checkFolder();
                            copyfile(getAdapterPosition());
                        }
                    }
                } else {
                    Intent i = new Intent(context, SlidingimageViewActivityreniew.class);
//                    i.putExtra("uri", filesList.get(getAdapterPosition()).getUri());
//                    i.putExtra("path", filesList.get(getAdapterPosition()).getPath());
//                    i.putExtra("title", filesList.get(getAdapterPosition()).getFilename());
                    i.putExtra("actionchk", actionchk);
                    i.putExtra("Current", getAdapterPosition());


                    context.startActivity(i);
                }
            }else {
                if (multiselecteditems != null) {

                    vib.vibrate(8);
                if (!multiselecteditems.contains(filesList.get(getAdapterPosition())))
                    {
                images_download.setImageResource(R.drawable.ic_baseline_check_circle_24);
                ImageViewCompat.setImageTintList(images_download, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));



                    Log.v("multi","    besfore add isds size is: "+ multiselecteditems.size());
multiselecteditems.add(filesList.get(getAdapterPosition()));
                        multiselectx.OnMultiselect(multiselecteditems);
                    Log.v("multi"," id added : "+getAdapterPosition()+",  And now size isds size is: "+ multiselecteditems.size());
                }
                else {

                        if (multiselecteditems.size() > 0) {
                            if (actionchk.equals("DW")) {
                                images_download.setImageResource(R.drawable.ic_baseline_delete_forever_24);
                            } else {
                                images_download.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24);
                            }
                            ImageViewCompat.setImageTintList(images_download, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));

                            Log.v("multi", "    besfore remove isds size is: " + multiselecteditems.size());

                            multiselecteditems.remove(filesList.get(getAdapterPosition()));
                            multiselectx.OnMultiselect(multiselecteditems);
                            Log.v("multi", " id removed : " + getAdapterPosition() + ",  And now size isds size is: " + multiselecteditems.size());
                       if (multiselecteditems.size()==0){
                           multicheck=false;
                       }
                        }
                    }
                }
                else {

                    images_download.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                    ImageViewCompat.setImageTintList(images_download, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));
                    Log.v("multi","    besfore add isds size is: null 1st time in add");
                    multiselecteditems.add(filesList.get(getAdapterPosition()));
                    multiselectx.OnMultiselect(multiselecteditems);

                    Log.v("multi"," id added : "+getAdapterPosition()+",  And now size isds size is: "+ multiselecteditems.size());

                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            multicheck=true;
            images_download.performClick();
            return false;
        }
    }

    private void checkFolder() {
        new FolderChecker(loc,context) ;

    }
    private void copyfile(int position){
        // file_from_whatsappfolders at path is saved to file_from_whatsappfolders

        final File file_from_whatsappfolders = new File(filesList.get(position).getPath());
        File destFile = new File(loc);
     //copies filoe
        try { FileUtils.copyFileToDirectory(file_from_whatsappfolders,destFile); } catch (IOException e) {
            e.printStackTrace();

        }
// scans the copided file
        MediaScannerConnection.scanFile(
                context,
                new String[]{ loc + file_from_whatsappfolders.getName()},
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


        Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show();
        new adsreq(context);
    }

    public  int gettingtint(int chk){
return  new gettingtintColor().gettingtint(chk);

    }


        private  void dellfile(String path, int pos){

            File file = new File(path);
            if (file.exists()) {
                if (file.delete()) {
                    filesList.remove(pos);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        }
}
