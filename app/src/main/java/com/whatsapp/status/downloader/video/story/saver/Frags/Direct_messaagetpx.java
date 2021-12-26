package com.whatsapp.status.downloader.video.story.saver.Frags;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;

import java.net.URLEncoder;
import java.util.ArrayList;

import com.hbb20.CountryCodePicker;
import com.whatsapp.status.downloader.video.story.saver.Adapter.SpinnercustomAdaptertpx;
import com.whatsapp.status.downloader.video.story.saver.Model.List_Ianguagestpx;
import com.whatsapp.status.downloader.video.story.saver.R;
import com.whatsapp.status.downloader.video.story.saver.Utills.gettingtintColor;

import static android.content.Context.MODE_PRIVATE;


public class Direct_messaagetpx extends Fragment implements  View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spin;
    List_Ianguagestpx item = null;
    LottieAnimationView MIC;
    EditText FromEDTV;
    String From_lanuge_code, To_lanuge_code;
    EditText numver;
    String num,data;
    int currenttheme;
    View view;
    CountryCodePicker ccp;



    ArrayList<List_Ianguagestpx> data_array = new ArrayList<>();

    public Direct_messaagetpx(int currenttheme) {
        this.currenttheme = currenttheme;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        inflater.getContext().setTheme(currenttheme);

        View view=inflater.inflate(R.layout.frag_direct_messaage, container, false);


        this.view=view;
        definingids();


        if (currenttheme==R.style.DarkMode){
            CardView c1=view.findViewById(R.id.c1);
            c1.setCardBackgroundColor(Color.parseColor("#696969"));

        }
        return  view;
    }

    private void definingids() {
numver=view.findViewById(R.id.num);
        ccp = (CountryCodePicker)view. findViewById(R.id.ccp);

        view.findViewById(R.id.send).setOnClickListener(this);
        view.findViewById(R.id.Micframe).setOnClickListener(this);
        spin = view.findViewById(R.id.spinner);
        FromEDTV = view.findViewById(R.id.fromEDTV);
        From_lanuge_code = "hi";
        To_lanuge_code = "hi";
        MIC = view.findViewById(R.id.MIC);
        MIC.setOnClickListener(this);
        spin.setOnItemSelectedListener(this);
       SpinnercustomAdaptertpx dataAdapter = new SpinnercustomAdaptertpx(getActivity(), getDATA());
  spin.setAdapter(dataAdapter);



    }

    private ArrayList<List_Ianguagestpx> getDATA() {
        data_array.clear();
        data_array.add(new List_Ianguagestpx("Hindi", "hi"));
        data_array.add(new List_Ianguagestpx("Urdu", "ur"));
        data_array.add(new List_Ianguagestpx("English", "en"));
        data_array.add(new List_Ianguagestpx("Portuguese", "pt"));
        data_array.add(new List_Ianguagestpx("Spanish/MX", "es"));
        data_array.add(new List_Ianguagestpx("Indonesian", "id"));
        data_array.add(new List_Ianguagestpx("Russian", "ru"));
        data_array.add(new List_Ianguagestpx("Spanish/global", "es"));
        data_array.add(new List_Ianguagestpx("German", "de"));
        data_array.add(new List_Ianguagestpx("Italian", "it"));
        data_array.add(new List_Ianguagestpx("Chinese", "zh"));
        data_array.add(new List_Ianguagestpx("Japanese", "ja"));
        data_array.add(new List_Ianguagestpx("French", "fr"));
        data_array.add(new List_Ianguagestpx("Arabic", "ar"));
        data_array.add(new List_Ianguagestpx("Polish ", "pl"));
        data_array.add(new List_Ianguagestpx("Tamil ", "ta"));
        data_array.add(new List_Ianguagestpx("Telugu", "te"));
        data_array.add(new List_Ianguagestpx("Malayalam ", "ml"));
        data_array.add(new List_Ianguagestpx("Ukrainian ", "uk"));
        data_array.add(new List_Ianguagestpx("Thai", "th"));
        data_array.add(new List_Ianguagestpx("Slovak", "sk"));
        return data_array;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    item = data_array.get(position);

        if (parent.getId() == R.id.spinner) {
            if (item != null) {
                From_lanuge_code = item.Language_code;

            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.MIC || id == R.id.Micframe) {
            Microphoneprocess();
        } else {
            num = numver.getText().toString();
            if (!TextUtils.isEmpty(num)) {
                data = FromEDTV.getText().toString().trim();
                if (!TextUtils.isEmpty(data)) {
                    gotowhatsapp();
                } else {
                    SnkBarr("Can't send Empty Message");
                }

            } else {
                SnkBarr("Invalid phone number");

            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7) {
            if ( null != data) {
                ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                FromEDTV.setText(result.get(0).toString().trim());
                result.clear();
                view.findViewById(R.id.send).callOnClick();
            }
        }
    }

    private void gotowhatsapp(){

          final Intent sendMsgxzintent = new Intent(Intent.ACTION_VIEW);

//        startActivity(new Intent(this, ForgotPasss.class)
//                .putExtra("User_name",name+", "+acttype));
switch (getActivity().getSharedPreferences("stts", MODE_PRIVATE).getString("def", "")){
    case "WA":
        sendMsgxzintent.setPackage("com.whatsapp");
        break;
    case "BWA":
        sendMsgxzintent.setPackage("com.whatsapp.w4b");
        break;
    case "GBWA":
        sendMsgxzintent.setPackage("com.gbwhatsapp");

        SnkBarr("You don't an official WA app");
        break;
    default:
        SnkBarr("You don't Have any whatsapp install in your phone");

        break;
}





            try{
                String ccode=   "91"  ;
                ccode=    ccp.getSelectedCountryCode();

                Log.v("ccp","ccp: "+ccode);
                num=ccode+num;

                Log.v("ccp","ccp:num "+num);
                String url = "https://api.whatsapp.com/send?phone="+ num +"&text=" + URLEncoder.encode(data, "UTF-8");

            sendMsgxzintent.setData(Uri.parse(url));

                Log.v("ccp","ccp: pkg "+getActivity().getPackageManager());
            if (sendMsgxzintent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(sendMsgxzintent);
            }

        } catch (Exception e) {

                Log.v("ccp","ccp: "+e);
        }
            if (getActivity().getSharedPreferences("stts", MODE_PRIVATE).getString("def", "").equals("GBWA")){
                SnkBarr("Select the default app as Wa or BWa");

            }
    }

    private void Microphoneprocess() {


        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, From_lanuge_code);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, From_lanuge_code);
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, From_lanuge_code);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, R.string.app_name + " Speak to type .... ");
        try {
            startActivityForResult(intent, 7);
        } catch (ActivityNotFoundException a) {
            SnkBarr("Sorry your device not supported this Feature");
        }
    }

    private void SnkBarr(String msg) {


        Snackbar snackbar;
        snackbar = Snackbar.make(view.findViewById(R.id.bg).getRootView(), msg, Snackbar.LENGTH_LONG);
         if (currenttheme!=R.style.DarkMode){
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(),
                    new gettingtintColor().gettingtint(currenttheme)));}
        else {
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.darkmodeblack));
        }
        snackbar.show();
    }
    //






}