package com.whatsapp.status.downloader.video.story.saver.Utills;


import com.whatsapp.status.downloader.video.story.saver.R;

public class gettingtintColor {


    public int gettingtintdrable(int chk) {

        if (chk == R.style.DarkMode) {
            return R.drawable.curvxdark;
        } else if (chk == R.style.Default) {
            return R.drawable.curvxorgdef;
        } else if (chk == R.style.yelloMode) {
            return R.drawable.curvxyello;
        } else if (chk == R.style.LightRed) {
            return R.drawable.curvxligtred;
        } else if (chk == R.style.PurpleMode) {
            return R.drawable.curvxpeurple;
        }else if (chk == R.style.Green) {
            return R.drawable.curvxgreen;
        }else if (chk == R.style.Sky) {
            return R.drawable.curvxsky;
        }else if (chk == R.style.coffe) {
            return R.drawable.curvxcoffe;
        }else if (chk == R.style.whitex) {
            return R.drawable.curvwhite;
        }

        return R.drawable.curvx;
    }
    public  int gettingtint(int chk){

        if (chk == R.style.DarkMode) {
            return R.color.clay;
        } else if (chk == R.style.Default) {
            return R.color.orange_app;
        } else if (chk == R.style.yelloMode) {
            return R.color.yellow;
        } else if (chk == R.style.LightRed) {
            return R.color.red;
        } else if (chk == R.style.PurpleMode) {
            return R.color.colorPrimary;
        } else if (chk == R.style.Green) {
            return R.color.greendefault;
        }else if (chk == R.style.Sky) {
            return R.color.skybluecolor;
        }else if (chk == R.style.coffe) {
            return R.color.coffecolor;
        }

        return R.color.black;
    }
}
