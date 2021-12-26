package com.whatsapp.status.downloader.video.story.saver.Frags;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Adapter.InboxAdaptertpx;
import com.whatsapp.status.downloader.video.story.saver.DB.DBtpx;
import com.whatsapp.status.downloader.video.story.saver.DB.GettingUserFromDB_Listtpx;
import com.whatsapp.status.downloader.video.story.saver.Model.List_Inbox;
import com.whatsapp.status.downloader.video.story.saver.Model.Tablename_image;
import com.whatsapp.status.downloader.video.story.saver.Model.WAnotifiacationtpx;
import com.whatsapp.status.downloader.video.story.saver.Notfificationlistner.NotificationSettingPermissiontpx;
import com.whatsapp.status.downloader.video.story.saver.R;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;

import static android.content.Context.MODE_PRIVATE;


public class MSGFragment extends Fragment
{

    SharedPreferences AI;
    RecyclerView recyclerView;
    InboxAdaptertpx InboxAdaptertpx;
    SwipeRefreshLayout refreshLayout;
    ArrayList<List_Inbox> inbox=null;

    int currenttheme;
    FrameLayout bg;

    public MSGFragment(int chk) {
        currenttheme=chk;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_msg_inbox, container, false);
        bg=view.findViewById(R.id.bg);

        recyclerView=view.findViewById(R.id.RecyclerViewmsg);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout =view.findViewById(R.id.swipeRecyclerViewmsg);
        refreshLayout.setColorSchemeColors(Color.GREEN, Color.BLACK, Color.BLUE, Color.YELLOW, Color.CYAN, Color.RED);
        inbox=new ArrayList<>();

        view.findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setIcon(R.drawable.logo)
                        .setTitle("   How this works?")

                        .setCancelable(false)
                        .setMessage("This feature allows you to recover deleted text messages from any WA, Business WA or GB WA account. It also provides you with the overall incoming message backup\nIn order to use this feature:\n\n1)Messengers notification should allowed.\n2)App should be provided all the required permissions.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {




                                dialog.dismiss();
                            }

                        })
                          .show();
            }
        });
        AI = getActivity().getSharedPreferences("AI", MODE_PRIVATE);

        new NotificationSettingPermissiontpx(getContext()).process();

        reciver();
        RefreshLoading();



        List();
        return view;
    }
int i=0;
    @Override
    public void onResume() {
        super.onResume();

           if (!ChheckNotificationisONN()) {

               Snackbar snackbarmsg = Snackbar.make(refreshLayout, "Permission required for this feature to work", Snackbar.LENGTH_LONG);
               if (currenttheme != R.style.DarkMode || currenttheme != R.style.whitex) {
                   snackbarmsg.getView().setBackgroundColor(ContextCompat.getColor(getActivity(),
                           new gettingtintColor().gettingtint(R.style.DarkMode)));
               } else {
                   snackbarmsg.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.darkmodeblack));
               }
               snackbarmsg.show();


           }

    }

    private void RefreshLoading()
    {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                inbox.clear();

                InboxAdaptertpx.notifyDataSetChanged();
                recyclerView.removeAllViewsInLayout();

                List();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Refreshed!", Toast.LENGTH_SHORT).show();



                    }
                }, 1000);

            }
        });
    }


    private void SetUpRecyclerView()
    {


        if (inbox !=null) {
            if (inbox.size()>0){
                Log.v("msgx","inbox ha ye: "+inbox.size());
            InboxAdaptertpx = new InboxAdaptertpx(getContext(), inbox,currenttheme);
            recyclerView.setAdapter(InboxAdaptertpx);
            InboxAdaptertpx.notifyDataSetChanged();}else {
                NotifyofNonewStatusmsg();
            }
        }

    }

















    private void NotifyofNonewStatusmsg(){
Log.v("msgx","inbox empty:---> No Messages Recived yet!");
        try {
        Snackbar snackbarmsg=    Snackbar.make( bg, "No Messages Recived yet! , Try checking your notifications are enabled", Snackbar.LENGTH_LONG);


            if (currenttheme != R.style.DarkMode) {
                snackbarmsg.getView().setBackgroundColor(ContextCompat.getColor(getActivity(),
                        new gettingtintColor().gettingtint(currenttheme)));
            } else {
                snackbarmsg.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.darkmodeblack));
            }


            snackbarmsg.show();
        }catch (Exception e){

        }
 }




    private boolean ChheckNotificationisONN()  {
     return new NotificationSettingPermissiontpx(getContext()).status();
    }

private  void  reciver(){
//    Toast.makeText(getContext(), "rcvr active", Toast.LENGTH_SHORT).show();


    IntentFilter filter = new IntentFilter("WAsyNoti");
     BroadcastReceiver smsBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "RCVd New -->", Toast.LENGTH_SHORT).show();


            List();

InboxAdaptertpx.notifyDataSetChanged();


        }
    };
    getActivity().registerReceiver(smsBroadcastReceiver, filter);
}



private void List(){



inbox.clear();
    String Msg="";
    String Name="";
    String Date="";
    Bitmap img;

    WAnotifiacationtpx item2;   Tablename_image item;

    ArrayList<Tablename_image> list= null;
        list=new GettingUserFromDB_Listtpx(getContext()).process();
       if (list!=null && list.size()>0) {

//           Toast.makeText(getActivity(), "Data availible to display", Toast.LENGTH_SHORT).show();

           Log.v("msgx", "inbox  Tablename_image data return from db is not empty "+list.size()  );
        for (int i=0; i<list.size(); i++) {
             item = list.get(i);
if (item!=null){
    if (item.Name.contains("+")){
        item.Name=item.Name.replace("+","");
    }  if (!item.Name.contains("name")){
        item.Name="name"+item.Name;
    }


               item2= new DBtpx(getContext(),item.Name.trim(),"WA","last").getLast();
    if (item2!=null) {
        Msg = item2.MSG;
        Date = item2.Date;
        Name = item2.Name;
        img = item.Img;

        Log.v("msgx", "user list is not null: " + Msg + " :--> " + Date + " :--> " + Name + " :--> " + img + " :--> " + "(" + item.Name + ")");
        inbox.add(new List_Inbox(Name, Date, Msg, img));


        Log.v("msgx","inbox ha ye ha at :"+i+"  ->"+inbox.size());
    }else {

        Log.v("msgx", "inbox  person data empty "  );

    }}else {

    Log.v("msgx", "inbox  person image empty "  );

}
        }

                }else {
           Log.v("msgx", "inbox  Tablename_image data return from db is empty "  );


       }


SetUpRecyclerView();
}
}
