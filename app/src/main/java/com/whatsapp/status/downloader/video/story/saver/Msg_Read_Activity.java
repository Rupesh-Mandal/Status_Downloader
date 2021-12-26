package com.whatsapp.status.downloader.video.story.saver;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Adapter.MSG_read_Adaptertpx;
import com.whatsapp.status.downloader.video.story.saver.DB.DBtpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAnotifiacationtpx;
import com.whatsapp.status.downloader.video.story.saver.Utills.themeapply;

public class Msg_Read_Activity extends AppCompatActivity implements View.OnClickListener {
RecyclerView recyclerView;
    Thread extraThread1;     MSG_read_Adaptertpx adapter;
    String Name;  Bitmap img;
SwipeRefreshLayout refreshLayout;
ArrayList<WAnotifiacationtpx> msgs_data=new ArrayList<>();
int chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chk = new themeapply().theme(getApplicationContext());
        setTheme(chk);

        if (chk==R.style.DarkMode){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.lightblk));
            }

        }
        setContentView(R.layout.activity_msg_read);
        Name=getIntent().getStringExtra("Name");
        if (Name.contains("+")){
            Name=Name.replace("+","");
        }  if (!Name.contains("name")){
            Name="name"+Name;
        }


        Log.v("name","name: msg read iska ha  "+Name);

     byte[] bytes=getIntent().getByteArrayExtra("Pic");

          img= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView dp=findViewById(R.id.dp);
        TextView name=findViewById(R.id.name); name.setText(Name);
dp.setImageBitmap(img);
findViewById(R.id.back).setOnClickListener(this);



        recyclerView=findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout =findViewById(R.id.swipeRecyclerViewmsg);
        refreshLayout.setColorSchemeColors(Color.GREEN, Color.BLACK, Color.BLUE, Color.YELLOW, Color.CYAN, Color.RED);
        RefreshLoading();
        ThreadStart();

    }



    private  void ThreadStart(){


        extraThread1 =new Thread(){
            @Override
            public void run() {
             LoadMSGS();
            }
        };
        extraThread1.start();

    }




    private void RefreshLoading()
    {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                recyclerView.removeAllViewsInLayout();
                recyclerView.setVisibility(View.GONE);
                msgs_data.clear();
                adapter.notifyDataSetChanged();
                stopuithreads();
                ThreadStart();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);

                        recyclerView.setVisibility(View.VISIBLE);

                    }
                }, 1000);

            }
        });
    }

    private void stopuithreads(){
        try {


            if (extraThread1 !=null){
                extraThread1 =null;
            }


        }catch (Exception e){
            Log.v("x",e.toString());
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopuithreads();
    }


    private  void LoadMSGS(){
        DBtpx DBtpx =new DBtpx(this,Name,"WA","wa");
        msgs_data= DBtpx.getAll();

         adapter=new MSG_read_Adaptertpx(getApplicationContext(),msgs_data,chk);
        recyclerView.setAdapter(adapter);
        return;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}