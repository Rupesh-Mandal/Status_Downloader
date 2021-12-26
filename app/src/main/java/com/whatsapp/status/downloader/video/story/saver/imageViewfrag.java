package com.whatsapp.status.downloader.video.story.saver;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.whatsapp.status.downloader.video.story.saver.Model.Constantstpx;
import com.whatsapp.status.downloader.video.story.saver.Model.WAImageModeltpx;
import com.whatsapp.status.downloader.video.story.saver.Utills.DownloadFile;
import com.whatsapp.status.downloader.video.story.saver.Utills.ShareContent;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;
import com.whatsapp.status.downloader.video.story.saver.Utills.themeapply;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

public class imageViewfrag extends Fragment {
    com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView imageView2;



    WAImageModeltpx pic;

    public imageViewfrag(WAImageModeltpx integer) {
        pic=integer;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sliding_image_view, container, false);


        imageView2=view.findViewById(R.id.x);


        imageView2.setImage(ImageSource.uri(pic.getUri()));
    return view;

    }












    }


