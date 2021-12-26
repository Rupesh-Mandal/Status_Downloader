package com.whatsapp.status.downloader.video.story.saver.Frags;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;
import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;
import com.whatsapp.status.downloader.video.story.saver.R;

import java.io.File;
import java.util.ArrayList;

import com.whatsapp.status.downloader.video.story.saver.Adapter.ImageAdaptertpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAImageModeltpx;
import com.whatsapp.status.downloader.video.story.saver.Utills.Calculatingrowstpx;
import com.whatsapp.status.downloader.video.story.saver.Utills.DownloadFile;
import com.whatsapp.status.downloader.video.story.saver.Utills.GetttingWhatsappFiles;
import com.whatsapp.status.downloader.video.story.saver.Utills.MultisendWhatsapp;
import com.whatsapp.status.downloader.video.story.saver.Utills.ShareContent;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;
import com.whatsapp.status.downloader.video.story.saver.callbacks.multiselect;

import static android.content.Context.MODE_PRIVATE;


public class ImageFragmenttpx extends Fragment implements multiselect, View.OnClickListener {
LinearLayout multiselectedoptions;
    Thread extraThread;
    RecyclerView recyclerView;
    ImageAdaptertpx imageAdaptertpx;
    RelativeLayout lyt;
    SwipeRefreshLayout refreshLayout;
    ArrayList<WAImageModeltpx> arrayList = null;
    String Type_of_whatsapp_chk;
    int currenttheme;

    public ImageFragmenttpx(int theme, String Type_of_whatsapp_chk) {
        this.Type_of_whatsapp_chk = Type_of_whatsapp_chk;
        currenttheme = theme;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflater.getContext().setTheme(currenttheme);

        arrayList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView = view.findViewById(R.id.recycle);
        refreshLayout = view.findViewById(R.id.swipeRecyclerView);

        multiselectedoptions=view.findViewById(R.id.multilioptlyt);
        if(Type_of_whatsapp_chk.equals("DW")){

            view.findViewById(R.id.fab4a).setVisibility(View.VISIBLE);
            view.findViewById(R.id.fab4).setVisibility(View.VISIBLE);

            view.findViewById(R.id.fab4).setOnClickListener(this);
        }else {

            view.findViewById(R.id.fab4a).setVisibility(View.GONE);
            view.findViewById(R.id.fab4).setVisibility(View.GONE);
        }
        view.findViewById(R.id.fab2).setOnClickListener(this);
        view.findViewById(R.id.fab1).setOnClickListener(this);
        view.findViewById(R.id.cancel).setOnClickListener(this);

        refreshLayout.setColorSchemeColors(Color.GREEN, Color.BLACK, Color.BLUE, Color.YELLOW, Color.CYAN, Color.RED);
        lyt = view.findViewById(R.id.lytimg);


        initailaizing();




        return view;
    }

        @Override
    public void onResume() {

        if (Type_of_whatsapp_chk.equals("DW")) {
            if (Type_of_whatsapp_chk.equals("DW")) {
                SetUpRecyclerView();
            }

        }


        super.onResume();
    }
    private void RefreshLoading() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                stopuithreads();
                refreshLayout.setRefreshing(true);
if (arrayList!=null){
                arrayList.clear();}
                imageAdaptertpx.notifyDataSetChanged();
                recyclerView.removeAllViewsInLayout();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "Refreshed!", Toast.LENGTH_SHORT).show();
                        initailaizing();

                    }
                }, 1000);

            }
        });
    }


    private void SetUpRecyclerView() {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), Calculatingrowstpx(getActivity(), 113)));


        getStatusimages(Type_of_whatsapp_chk);

        if (arrayList != null) {

            if (arrayList.size() >= 1) {
                imageAdaptertpx = new ImageAdaptertpx(getActivity(),this, arrayList, currenttheme, Type_of_whatsapp_chk);
                recyclerView.setAdapter(imageAdaptertpx);
                imageAdaptertpx.notifyDataSetChanged();
            } else {
                NotifyofNonewStatus();
            }


        }

    }


    public void getStatusimages(String chks) {
        if (arrayList != null) {
            arrayList.clear();
        }
        if (chks.equals("DW")) {

            arrayList = new GetttingWhatsappFiles(getActivity()).saved_imagesprocess(chks);
        } else {
            arrayList = new GetttingWhatsappFiles(getActivity()).imagesprocess(chks);

        }
//        if (arrayList != null) {
//            for (int i = 0; i < arrayList.size(); i++) {
//                Log.v("hassan", " this is the data recived: at :->" + i + "->  :" + arrayList.get(i).getPath());
//            }
//        } else {
//
//            Log.v("hassan", " this is the data recived: array is null");
//        }

    }

    private int Calculatingrowstpx(Context contextt, float columnWidthDpz) {
        return new Calculatingrowstpx(contextt, columnWidthDpz).Calculating();

    }


    private void initailaizing() {

        extraThread = new Thread() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SetUpRecyclerView();
                        RefreshLoading();
                    }
                });
            }
        };
        extraThread.start();


    }


    private void NotifyofNonewStatus() {

        Snackbar snackbar = Snackbar.make(lyt, "You need to view  status in 'WA app' in order to download it here", Snackbar.LENGTH_LONG);

        if (currenttheme != R.style.DarkMode) {
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(),
                    new gettingtintColor().gettingtint(currenttheme)));
        } else {
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.darkmodeblack));
        }

        snackbar.show();
    }

    private void stopuithreads() {
        try {
            if (extraThread != null) {
                extraThread = null;
                extraThread = null;
            }


        } catch (Exception e) {
            Log.v("x", e.toString());
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        stopuithreads();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        stopuithreads();


    }

    ArrayList<WAImageModeltpx> multiselecteditems;
    @Override
    public void OnMultiselect(ArrayList<WAImageModeltpx> multiselectedDATA) {

        Log.v("multi"," rcv active ");

        if (multiselectedDATA !=null){


            if (multiselectedDATA.size()>0){

                multiselecteditems=multiselectedDATA;

                Log.v("multi"," rcv size is: "+ multiselecteditems.size());

                multiselectedoptions.setVisibility(View.VISIBLE);


            }else {

                multiselectedoptions.setVisibility(View.GONE);

            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.fab2){

            multiwhatsapp();
        }else if (view.getId()==R.id.fab1){
            download();
            
        }else if (view.getId()==R.id.fab4){
            dell();

        }else if (view.getId()==R.id.cancel){

            multiselectedoptions.setVisibility(View.GONE);
            SetUpRecyclerView();

        }
        
    }

    private void download() {
       String  loc=getActivity().getSharedPreferences("settings",MODE_PRIVATE).getString("loc", Environment.getExternalStorageDirectory().getAbsolutePath() + Constantstpx.SAVE_FOLDER_NAME);

        Log.v("multi"," in download");
        for (int i=0; i<multiselecteditems.size(); i++){

            new DownloadFile(getActivity(), loc, multiselecteditems.get(i).getPath(), multiselecteditems.get(i).getFilename(),true);
        }

        Toast.makeText(getActivity(), "Saved!", Toast.LENGTH_SHORT).show();
        multiselectedoptions.setVisibility(View.GONE);
        SetUpRecyclerView();
    }

    private void dell() {

        Log.v("multi"," in dell");
 for (int i=0; i<multiselecteditems.size(); i++){

     File file = new File(multiselecteditems.get(i).getPath());
     if (file.exists()) {
    file.delete();

 }



        }

        Toast.makeText(getActivity(), "Deleted!", Toast.LENGTH_SHORT).show();
 multiselectedoptions.setVisibility(View.GONE);
        SetUpRecyclerView();
    }

    private void multiwhatsapp() {
        ArrayList<Uri> uris=new ArrayList<>();
         for (int i=0; i<multiselecteditems.size(); i++){
             uris.add(  FileProvider.getUriForFile(getActivity(),  ".fileprovider", new File(multiselecteditems.get(i).getPath())));
         }
     new MultisendWhatsapp(getActivity()).multiimagesend(uris);
        multiselectedoptions.setVisibility(View.GONE);
        SetUpRecyclerView();
    }


}