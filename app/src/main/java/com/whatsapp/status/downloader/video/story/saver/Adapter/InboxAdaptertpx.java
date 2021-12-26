package com.whatsapp.status.downloader.video.story.saver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.whatsapp.status.downloader.video.story.saver.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Model.List_Inbox;
import com.whatsapp.status.downloader.video.story.saver.Msg_Read_Activity;

public class InboxAdaptertpx extends RecyclerView.Adapter<InboxAdaptertpx.HolderOftxt> {

    Context context;
    ArrayList<List_Inbox> inbox_data;
    int currentTheme;
    public InboxAdaptertpx(Context context, ArrayList<List_Inbox> inbox_data, int chk)
    {
        this.context = context;
        this.inbox_data = inbox_data;
        currentTheme=chk;

    }



    @NonNull
    @Override
    public HolderOftxt onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = null;
        if (currentTheme==R.style.DarkMode){

            view= LayoutInflater.from(context).inflate(R.layout.custom_item_inbox_dark,parent,false);
        }else {
            view= LayoutInflater.from(context).inflate(R.layout.custom_item_inbox,parent,false);}
        return new HolderOftxt(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderOftxt holder, final int position)
    {
        List_Inbox item=inbox_data.get(position);
     holder.Message.setText(item.Message);
        holder.Name.setText(item.Name);
        holder.date.setText(item.Date);
        holder.picl.setImageBitmap((item).Img);

    }

    @Override
    public int getItemCount()

    {
        return inbox_data.size();
    }





    public class HolderOftxt extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView picl;
        TextView Name, date, Message;
        Context cnt;

        public HolderOftxt(@NonNull View itemView) {
            super(itemView);
            cnt = itemView.getContext();

            Name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            Message = itemView.findViewById(R.id.message);
            picl = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            List_Inbox item = inbox_data.get(getAdapterPosition());

          try {   ByteArrayOutputStream stream = new ByteArrayOutputStream();

                item.Img.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();


                context.startActivity(new Intent(context, Msg_Read_Activity.class)
                        .putExtra("Name", item.Name)
                        .putExtra("Pic", byteArray)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }catch (Exception e){
              Toast.makeText(cnt, "Data of this user is corrupt\n try reinstalling the com or clearing all the data", Toast.LENGTH_SHORT).show();
          }





        }

    }




}
