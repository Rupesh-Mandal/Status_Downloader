package com.whatsapp.status.downloader.video.story.saver.Utills;

import android.content.Context;
import android.util.DisplayMetrics;

public class Calculatingrowstpx {
Context contextt;
float Column_Witdth_in_DP;  float Screen_WIdth_in_DP;
    int Number_of_columns_in_dp_return;
    DisplayMetrics dispMtrc;
    public Calculatingrowstpx(Context contextt, float columnWidthINDpz) {
        this.contextt = contextt;
        this.Column_Witdth_in_DP = columnWidthINDpz;
    }



    public int Calculating() {
         dispMtrc = contextt.getResources().getDisplayMetrics();
         Screen_WIdth_in_DP= dispMtrc.widthPixels / dispMtrc.density;
         Number_of_columns_in_dp_return = (int) (Screen_WIdth_in_DP / Column_Witdth_in_DP + 0.5);
        return Number_of_columns_in_dp_return;
    }

}
