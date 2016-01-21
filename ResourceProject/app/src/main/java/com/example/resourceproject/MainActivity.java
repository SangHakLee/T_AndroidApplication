package com.example.resourceproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readRawData(R.raw.sam);
    }

    void readRawData(int resId){
        BufferedReader br =  new BufferedReader(new InputStreamReader(getResources().openRawResource(resId)));
//        br.readLine()
    };
}
