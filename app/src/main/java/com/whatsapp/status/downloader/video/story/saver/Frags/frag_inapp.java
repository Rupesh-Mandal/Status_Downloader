package com.whatsapp.status.downloader.video.story.saver.Frags;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.whatsapp.status.downloader.video.story.saver.R;
import com.whatsapp.status.downloader.video.story.saver.Utills.Constants;

import java.util.Arrays;
import java.util.List;


public class frag_inapp extends Fragment implements  View.OnClickListener, PurchasesUpdatedListener {
    TextView tv;

    int currenttheme;

    List<SkuDetails> skuDetails;
    BillingClient billingClient;
    BillingFlowParams billingFlowParams;
boolean suscribed=false;
    public frag_inapp(int chk, boolean suscribed) {
        currenttheme = chk;
        this.suscribed=suscribed;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflater.getContext().setTheme(currenttheme);

        View view = inflater.inflate(R.layout.frag_inapp, container, false);
        tv = view.findViewById(R.id.fromEDTV);

        view.findViewById(R.id.buy).setOnClickListener(this);
        if (currenttheme == R.style.DarkMode) {
            CardView c1 = view.findViewById(R.id.c1q);
            c1.setCardBackgroundColor(Color.parseColor("#696969"));

        }
        if (suscribed==false){
        setupbilling();}else {
            tv.setText("You are already subscribed");
            view.findViewById(R.id.buy).setClickable(false);
        }


        return view;
    }

    private void setupbilling() {
        ////1st thing we do
        billingClient = BillingClient.newBuilder(getActivity()).setListener(this).enablePendingPurchases().build(); // listner activated
        billingClient.startConnection(new BillingClientStateListener() {

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult responseCode) {
                if (responseCode.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    Toast.makeText(getActivity(), "Purchase System is secured and ready to work", Toast.LENGTH_SHORT).show();
                    loadproductingoogleplay();
                } else {
                    Log.v("bil", "connection fail:  " + responseCode);
                    Toast.makeText(getActivity(), "Purchase System malfunctioned", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                Toast.makeText(getActivity(), "Purchase System offline", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void loadproductingoogleplay() {
//        ..2nd stp in main act after setupbiiliunt
        if (billingClient.isReady()) {
            SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder().setSkusList(Arrays.asList("purchase_ads_free"))
                    .setType(BillingClient.SkuType.INAPP)
                    .build();//cals inapppurchase
            billingClient.querySkuDetailsAsync(skuDetailsParams, (responseCode, skuDetailsList) -> {
//                if (responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.v("bil", " billng is  ready your product is: " + skuDetailsList.size());


                    gettinglist(skuDetailsList);
//                } else {
//
//                    Log.v("bil", "billing is failed no product available: " + skuDetailsList.size());
//                }
            });


        } else {

            Toast.makeText(getActivity(), "Purchase System malfunctioned", Toast.LENGTH_SHORT).show();
            //refer sttep 1
        }
    }


    /////ending process

    private void gettinglist(List<SkuDetails> skuDetailsList) {
//        /
        //  this id the final step, we get our product from play store to show but we only have one product of 1$
        if (skuDetailsList != null) {
            if (skuDetailsList.size() > 0) {
                tv.setText(skuDetailsList.get(0).getTitle() + "\n" + skuDetailsList.get(0).getDescription() + "\n" + skuDetailsList.get(0).getPrice());

                this.skuDetails = skuDetailsList;
            }else {

//               getActivity(). getSharedPreferences("ads", Context.MODE_PRIVATE).edit().putBoolean("suscribed",true).apply();
            }

        } else {

            Log.v("bil", "purchase detail: not available no subscription available  ");
        }
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buy) {
            if (billingClient.isReady()) {
                loadpaymentmethode();
            } else {
                Toast.makeText(getActivity(), "Not available right now, comming soon ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadpaymentmethode() {
        //final step
        if (skuDetails != null) {
            if (skuDetails.size() > 0) {
                billingFlowParams = BillingFlowParams.newBuilder().setSkuDetails(skuDetails.get(0))
                        .build();
                billingClient.launchBillingFlow(getActivity(), billingFlowParams);
            }
        }
    }


    @SuppressLint("SetTextI18n")
    void handlePurchase(Purchase purchase) {
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            //This is for Non-Consumable product
            getActivity().getSharedPreferences("ads", Context.MODE_PRIVATE).edit().putBoolean("suscribed",true).apply();

            AcknowledgePurchaseParams acknowledgePurchaseParams =
                    AcknowledgePurchaseParams
                            .newBuilder()
                            .setPurchaseToken(purchase.getPurchaseToken())
                            .build();
            billingClient.acknowledgePurchase(acknowledgePurchaseParams,
                    billingResult -> {
                        tv.setText("Your purchase is verified, Please Restart the app");
                        Constants.SUSCRIBED=true;
                        getActivity().getSharedPreferences("ads", Context.MODE_PRIVATE).edit().putBoolean("suscribed",true).apply();

                    });
        }

        if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
            tv.setText("Your purchase is in process by playstore, once it is cleared you will be ads free");
            Constants.SUSCRIBED=true;
        }

    }
    @Override
    public void onPurchasesUpdated(@NonNull BillingResult responseCode, @Nullable List<Purchase> purchases) {
//after on click
        if (purchases != null) {
            Log.v("bil", "purchase report:  " + responseCode);
            Log.v("bil", "purchase detail:  " + purchases.get(0));
        } else {

            Log.v("bil", "purchase report:  null");
        }
        if (responseCode.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            Log.v("bil", "purchase report:  success purchase");
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
            getActivity().getSharedPreferences("ads", Context.MODE_PRIVATE).edit().putBoolean("suscribed",true).apply();

        } else if (responseCode.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(getActivity(), "purchase cancelled", Toast.LENGTH_SHORT).show();
            Log.v("bil", "purchase report:  filed purchase");
        }

    }
}