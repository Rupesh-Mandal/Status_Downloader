package com.whatsapp.status.downloader.video.story.saver.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whatsapp.status.downloader.video.story.saver.R;

import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Model.List_Ianguagestpx;

public class SpinnercustomAdaptertpx extends BaseAdapter {
    Context context;
    ArrayList<List_Ianguagestpx> data;
    LayoutInflater layoutInflater;

    public SpinnercustomAdaptertpx(Context context, ArrayList<List_Ianguagestpx> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.custom_item_spinner, null);
         TextView Language = convertView.findViewById(R.id.txt);
         List_Ianguagestpx item=data.get(position);
        Language.setText(item.Language_name);
        return convertView;
    }
}
