package com.sahidul.prgnacycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Bmi_Cal_Activity extends AppCompatActivity {

    com.google.android.gms.ads.AdView adView;
    InterstitialAd mInterstitialAd;
    EditText edWight, edHight1, edHight2;
    Button buttonResult;
    TextView tvDesply, tvDesply2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_cal);



        ////// AddView  /////

        adView = findViewById(R.id.adView);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        ////// End ///////



        edWight = findViewById(R.id.edWight);
        edHight1 = findViewById(R.id. edHight1);
        edHight2 = findViewById(R.id.edHight2);
        buttonResult = findViewById(R.id.buttonResult);
        tvDesply = findViewById(R.id.tvDesply);
        tvDesply2 = findViewById(R.id.tvDesply2);


        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String swight= edWight.getText().toString();
                String shight1= edHight1.getText().toString();
                String shight2= edHight2.getText().toString();

                if (swight.isEmpty()){
                    edWight.setError("আপনার ওজন দিন");
                } else if (shight1.isEmpty()){
                    edHight1.setError("আপনার উচ্চতা দিন");
                } else if (shight2.isEmpty()){
                    edHight2.setError("আপনার উচ্চতা দিন");
                } else {
                    float wight= Float.parseFloat(swight);
                    float hight1= Float.parseFloat(shight1);
                    float hight2= Float.parseFloat(shight2);

                    float hight= (float) (hight1*0.3048 + hight2*0.0254);
                    float bmiIndex= wight/hight/hight;

                    tvDesply.setText("Your BMI is = "+bmiIndex);

                    if (bmiIndex >= 18.5 && bmiIndex <= 25.0) {
                        tvDesply2.setText("আপনার ওজন স্বাভাবিক");
                    } else if (bmiIndex <= 18.5) {
                        tvDesply2.setText("আপনার ওজন কম, ডাক্তার এর পরামর্শ নেন ।");
                    } else if (bmiIndex >= 25.0 && bmiIndex <= 30.0) {
                        tvDesply2.setText("আপনার ওজন বেশি, ডাক্তার এর পরামর্শ নেন ।");
                    } else if (bmiIndex > 30.0) {
                        tvDesply2.setText("আপনার ওজন অতিরিক্ত বেশি, ডাক্তার এর পরামর্শ নেন ।");
                    }
                }



            }
        });


    }







}