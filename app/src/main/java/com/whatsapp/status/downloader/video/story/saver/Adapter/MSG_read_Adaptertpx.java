package com.whatsapp.status.downloader.video.story.saver.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.whatsapp.status.downloader.video.story.saver.R;

import java.util.ArrayList;
import java.util.Locale;

import com.whatsapp.status.downloader.video.story.saver.Model.WAnotifiacationtpx;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MSG_read_Adaptertpx extends RecyclerView.Adapter<MSG_read_Adaptertpx.HolderOftxt> {

    Context context;
    ArrayList<WAnotifiacationtpx> MSG_data;
    WAnotifiacationtpx item;
    int currentTheme;
    public MSG_read_Adaptertpx(Context context, ArrayList<WAnotifiacationtpx> MSG_data, int chk)
    {
        this.context = context;
        this.MSG_data = MSG_data;
        currentTheme=chk;

    }



    @NonNull
    @Override
    public HolderOftxt onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = null;
        if (currentTheme==R.style.DarkMode){

            view= LayoutInflater.from(context).inflate(R.layout.custom_item_inbox_reading_dark,parent,false);
        }else {

          view= LayoutInflater.from(context).inflate(R.layout.custom_item_inbox_reading,parent,false);}
        return new HolderOftxt(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderOftxt holder, final int position)
    {
        ImageViewCompat.setImageTintList(holder.cop, ColorStateList.valueOf(ContextCompat.getColor(context,  gettingtint(currentTheme))));
        ImageViewCompat.setImageTintList(holder.voic, ColorStateList.valueOf(ContextCompat.getColor(context, gettingtint(currentTheme))));

        item= MSG_data.get(position);
     holder.Message.setText(item.MSG);
        holder.date.setText(item.Date);

    }

    @Override
    public int getItemCount()

    {
        return MSG_data.size();
    }





    public class HolderOftxt extends RecyclerView.ViewHolder implements View.OnClickListener {

TextView date,Message;
        ImageView voic,cop;

        public HolderOftxt(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.date);
            Message=itemView.findViewById(R.id.message);
            cop = itemView.findViewById(R.id.cop);
            voic = itemView.findViewById(R.id.voic);
            cop.setOnClickListener(this);
            voic.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            item= MSG_data.get(getAdapterPosition());
            int id = v.getId();
            if (id == R.id.voic) {
                ConvertTextToSpeech(item.MSG);
            } else if (id == R.id.cop) {
                try {

                    item = MSG_data.get(getAdapterPosition());


                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Message Copied from " + R.string.app_name, item.MSG);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(context, "Message Copied!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                }
            }
        }


    }






    TextToSpeech tts = null;
    private void ConvertTextToSpeech(String text) {


        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("msgx", "This Language is not supported");
                    } else {
                        if (!TextUtils.isEmpty(text)) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                            } else {
                                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                    }
                } else {
                    Log.e("error", "Initilization Failed!");
                }
            }
        });

    }
    public  int gettingtint(int chk){
        return  new gettingtintColor().gettingtint(chk);

    }

}
