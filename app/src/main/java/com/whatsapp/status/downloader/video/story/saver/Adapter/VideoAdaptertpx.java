package com.whatsapp.status.downloader.video.story.saver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaScannerConnection;
import android.net.Uri;
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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Frags.VideoFragmenttpx;
import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAImageModeltpx;
import com.whatsapp.status.downloader.video.story.saver.R;
import com.whatsapp.status.downloader.video.story.saver.Utills.FolderChecker;
import com.whatsapp.status.downloader.video.story.saver.Utills.SlidingvideoViewActivityreniew;
import com.whatsapp.status.downloader.video.story.saver.Utills.adsreq;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;
import com.whatsapp.status.downloader.video.story.saver.VideoViewActivitytpx;
import com.whatsapp.status.downloader.video.story.saver.callbacks.multiselect;
import com.whatsapp.status.downloader.video.story.saver.callbacks.multiselect2;

import static android.content.Context.MODE_PRIVATE;

public class VideoAdaptertpx extends RecyclerView.Adapter<VideoAdaptertpx.ProgHolder> {

    boolean multicheck = false;
    ArrayList<WAImageModeltpx> multiselecteditems = new ArrayList<>();
    String loc;
    int currentTheme;
    String actionchk = "";

    Vibrator vib;
    Context context;
    ArrayList<WAImageModeltpx> filesList;
    multiselect2 multiselectx;

    public VideoAdaptertpx(Context context, VideoFragmenttpx videoFragmenttpx, ArrayList<WAImageModeltpx> filesList, int chk, String type_of_whatsapp_chk) {
        this.context = context;
        this.filesList = filesList;
        currentTheme = chk;

        multiselectx = videoFragmenttpx;
        loc = context.getSharedPreferences("settings", MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);
        actionchk = type_of_whatsapp_chk;
    }

    @NonNull
    @Override
    public ProgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_videos, parent, false);


        return new ProgHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ProgHolder holder, final int position) {
        if (!TextUtils.isEmpty(actionchk)) {
            if (actionchk.equals("DW")) {
                holder.video_downloadbutton.setImageResource(R.drawable.ic_baseline_delete_forever_24);
            }
        }
        ImageViewCompat.setImageTintList(holder.video_downloadbutton, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));
        ImageViewCompat.setImageTintList(holder.play, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));
        Glide.with(context).load(filesList.get(position).getUri()).into(holder.videoView);

    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }

    public class ProgHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView videoView;
        ImageView video_downloadbutton;
        CardView frameeLayout;
        ImageView play;

        public ProgHolder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.video_custom);
            video_downloadbutton = itemView.findViewById(R.id.video_download);
            frameeLayout = itemView.findViewById(R.id.layout_custom_video);

            play = itemView.findViewById(R.id.play_custom_video);
            play.setOnLongClickListener(this);
            video_downloadbutton.setOnLongClickListener(this);
            videoView.setOnLongClickListener(this);
            vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);


            video_downloadbutton.setOnClickListener(this);

            videoView.setOnClickListener(this);
            play.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (!multicheck) {

                if (v.getId() == R.id.video_download) {
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
                    Intent i = new Intent(context, SlidingvideoViewActivityreniew.class);
//                    i.putExtra("uri", Uri.parse(filesList.get(getAdapterPosition()).getPath()));
//                    i.putExtra("path", filesList.get(getAdapterPosition()).getPath());
//                    i.putExtra("title", filesList.get(getAdapterPosition()).getFilename());

                    i.putExtra("actionchk", actionchk);
                    i.putExtra("Current", getAdapterPosition());

                    context.startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            } else {
                if (multiselecteditems != null) {

                    vib.vibrate(8);
                    if (!multiselecteditems.contains(filesList.get(getAdapterPosition()))) {
                        video_downloadbutton.setImageResource(R.drawable.ic_baseline_check_circle_24);
                        ImageViewCompat.setImageTintList(video_downloadbutton, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));


                        Log.v("multi", "    besfore add isds size is: " + multiselecteditems.size());
                        multiselecteditems.add(filesList.get(getAdapterPosition()));
                        multiselectx.OnMultiselect(multiselecteditems);
                        Log.v("multi", " id added : " + getAdapterPosition() + ",  And now size isds size is: " + multiselecteditems.size());
                    } else {

                        if (multiselecteditems.size() > 0) {
                            if (actionchk.equals("DW")) {
                                video_downloadbutton.setImageResource(R.drawable.ic_baseline_delete_forever_24);
                            } else {
                                video_downloadbutton.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24);
                            }
                            ImageViewCompat.setImageTintList(video_downloadbutton, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));

                            Log.v("multi", "    besfore remove isds size is: " + multiselecteditems.size());

                            multiselecteditems.remove(filesList.get(getAdapterPosition()));
                            multiselectx.OnMultiselect(multiselecteditems);
                            Log.v("multi", " id removed : " + getAdapterPosition() + ",  And now size isds size is: " + multiselecteditems.size());
                            if (multiselecteditems.size() == 0) {
                                multicheck = false;
                            }
                        }
                    }
                } else {

                    video_downloadbutton.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                    ImageViewCompat.setImageTintList(video_downloadbutton, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));
                    Log.v("multi", "    besfore add isds size is: null 1st time in add");
                    multiselecteditems.add(filesList.get(getAdapterPosition()));
                    multiselectx.OnMultiselect(multiselecteditems);

                    Log.v("multi", " id added : " + getAdapterPosition() + ",  And now size isds size is: " + multiselecteditems.size());

                }
            }

        }

        @Override
        public boolean onLongClick(View view) {
            multicheck = true;
            video_downloadbutton.performClick();
            return false;
        }
    }
        private void checkFolder() {
            new FolderChecker(loc, context);

        }

        private void copyfile(int position) {
            // file_from_whatsappfolders at path is saved to file_from_whatsappfolders

            final File file_from_whatsappfolders = new File(filesList.get(position).getPath());
            File destFile = new File(loc);
            //copies filoe
            try {
                FileUtils.copyFileToDirectory(file_from_whatsappfolders, destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
// scans the copided file
            MediaScannerConnection.scanFile(
                    context,
                    new String[]{loc + file_from_whatsappfolders.getName()},
                    new String[]{"*/*"},
                    new MediaScannerConnection.MediaScannerConnectionClient() {
                        public void onMediaScannerConnected() {
                        }

                        public void onScanCompleted(String path, Uri uri) {
                            Log.d("path: ", path);
                        }
                    });
            Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show();
            new adsreq(context);

        }


        public int gettingtint(int chk) {
            return new gettingtintColor().gettingtint(chk);

        }

        private void dellfile(String path, int pos) {

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
