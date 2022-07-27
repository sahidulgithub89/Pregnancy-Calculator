package com.sahidul.prgnacycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import soup.neumorphism.NeumorphCardView;

public class Pregnancy_Cal_Activity extends AppCompatActivity {

    com.google.android.gms.ads.AdView adView;


    private EditText dateEditText,monthEditText,yearEditText;
    private Button submitButton;
    private TextView dateTextView,periodTextView;
    private LocalDate conciveDate;
    private LocalDate currentDate;

    NeumorphCardView visit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnancy_cal);



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



        dateEditText=findViewById(R.id.etBirhdayDay);
        monthEditText=findViewById(R.id.etBirhdayMonth);
        yearEditText=findViewById(R.id.etBirhdayYear);
        submitButton=findViewById(R.id.buttonCalculate);
        visit=findViewById(R.id.visit);
        dateTextView=findViewById(R.id.dateTextViewId);
        periodTextView=findViewById(R.id.periodTextViewId);
        dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()==1){
                    if (Integer.parseInt(charSequence+"")>3){
                        dateEditText.setText("0"+charSequence);
                        monthEditText.requestFocus();
                    }
                }
                if (charSequence.length()==2){
                    if (Integer.parseInt(charSequence+"")>31){
                        dateEditText.setText("31");
                    }
                    monthEditText.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        monthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()==1){
                    if (Integer.parseInt(charSequence+"")>2){
                        monthEditText.setText("0"+charSequence);
                        yearEditText.requestFocus();
                    }
                }
                if (charSequence.length()==2){
                    if (Integer.parseInt(charSequence+"")>12){
                        monthEditText.setText("12");
                    }
                    yearEditText.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        submitButton.setOnClickListener(view -> {
            String date=dateEditText.getText().toString();
            String month=monthEditText.getText().toString();
            String year=yearEditText.getText().toString();
            if (date.isEmpty()){
                dateEditText.requestFocus();
                dateEditText.setError("Enter Date");
            }
            if (month.isEmpty()){
                monthEditText.requestFocus();
                monthEditText.setError("Enter Month");
            }
            if (year.isEmpty()){
                yearEditText.setError("Year Correctly");
                yearEditText.requestFocus();
            }if (!date.isEmpty()&&!month.isEmpty()&&!year.isEmpty()&&year.length()==4){
                int dateInt=Integer.parseInt(date);
                int monthInt=Integer.parseInt(month);
                int yearInt=Integer.parseInt(year);
                try {
                    conciveDate=LocalDate.of(yearInt,monthInt,dateInt);
                }catch (Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Calendar calendar=Calendar.getInstance();
                currentDate=LocalDate.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
                if(!conciveDate.isBefore(currentDate)){
                    Toast.makeText(Pregnancy_Cal_Activity.this, "Check Date", Toast.LENGTH_SHORT).show();
                }
                else{
                    Period period=conciveDate.until(currentDate);
                    if (period.getYears()>=1){
                        Toast.makeText(this, "Ck Your Date Correctly... Pregnancy duration must bellow 1 year.", Toast.LENGTH_SHORT).show();
                    }else {
                        int time=280;
                        LocalDate resultDate=conciveDate.plusDays(time);
                        dateTextView.setText(resultDate.getDayOfMonth()+"-"+resultDate.getMonth()+"-"+resultDate.getYear());
                        periodTextView.setText(period.getMonths()+" মাস "+period.getDays()+" দিন");
                    }

                    visit.setVisibility(View.VISIBLE);
                }

            }else {
                Toast.makeText(this, "Ck date correctly", Toast.LENGTH_SHORT).show();
            }

        });



    }
}