package com.example.viewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inflation 직접 생성하기
        // 1. 직접 생성
//        LinearLayout view = new LinearLayout(this); // context 객체 무조건 필요 여기선 this
//        view.setBackgroundColor(Color.RED);
//        Button btn = new Button(this); // 여기서도 context 객체 필요
//        btn.setText("New Button");
//
//        view.addView(btn);
//        setContentView(view);
        //

        // 2. inflater
//        LayoutInflater inflater =(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//        LinearLayout view =  (LinearLayout)inflater.inflate(R.layout.sam, null); // 명시적 캐스팅
////        View view =  inflater.inflate(R.layout.sam, null); // 묵시적 캐스팅
//        setContentView(view);

        // 3.
        View view = View.inflate(this, R.layout.sam, null);
        setContentView(view);









//        setContentView(R.layout.activity_main);
    }
}
