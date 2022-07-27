package com.sahidul.prgnacycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class Home_Activity extends AppCompatActivity {

    com.google.android.gms.ads.AdView adView;
    InterstitialAd mInterstitialAd;
    ImageSlider imageSlider;
    NeumorphCardView pregnId,pregn_Care_Id,baby_groth_Id,baby_Care_Id,ageCalId,bmiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        /////// ImageSlider //////
        imageSlider = findViewById(R.id.image_slider);
        pregnId = findViewById(R.id.pregnId);
        pregn_Care_Id = findViewById(R.id.pregn_Care_Id);
        baby_groth_Id = findViewById(R.id.baby_groth_Id);
        baby_Care_Id = findViewById(R.id.baby_Care_Id);
        ageCalId = findViewById(R.id.ageCalId);
        bmiId = findViewById(R.id.bmiId);

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.p1, "", null));
        imageList.add(new SlideModel(R.drawable.p2, "", null));
        imageList.add(new SlideModel(R.drawable.s1, "", null));
        imageList.add(new SlideModel(R.drawable.s3, "", null));
        imageList.add(new SlideModel(R.drawable.s2, "", null));
        imageList.add(new SlideModel(R.drawable.p2, "", null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);

        /////// End //////



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


        pregnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Home_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                Intent intent=new Intent(Home_Activity.this,Pregnancy_Cal_Activity.class);
                startActivity(intent);
            }
        });


        pregn_Care_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Home_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                Intent intent=new Intent(Home_Activity.this,Pregnancy_Care_Activity.class);
                startActivity(intent);
            }
        });


        baby_groth_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Home_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                Intent intent=new Intent(Home_Activity.this,Baby_Groth_Activity.class);
                startActivity(intent);
            }
        });


        baby_Care_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Home_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                Intent intent=new Intent(Home_Activity.this,Baby_Care_Activity.class);
                startActivity(intent);
            }
        });


        ageCalId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Home_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                Intent intent=new Intent(Home_Activity.this,Age_Cal_Activity.class);
                startActivity(intent);
            }
        });


        bmiId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Home_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
                Intent intent=new Intent(Home_Activity.this,Bmi_Cal_Activity.class);
                startActivity(intent);
            }
        });




        loadFullscreenAd();


    }


    // loadFullscreenAd method starts here.....
    private void loadFullscreenAd(){

        //Requesting for a fullscreen Ad
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-9590411212206173/7543902119", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;


                //Fullscreen callback || Requesting again when an ad is shown already
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.

                        //User dismissed the previous ad. So we are requesting a new ad here
                        loadFullscreenAd();
                    }

                }); // FullScreen Callback Ends here


            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error

                mInterstitialAd = null;
            }

        });

    }
    // loadFullscreenAd method ENDS  here..... >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}