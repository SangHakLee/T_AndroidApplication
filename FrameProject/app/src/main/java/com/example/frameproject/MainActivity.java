package com.example.frameproject;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View v1, v2, v3;
    View.OnTouchListener handler = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(v.getId()){
                case R.id.textView :
                    v1.setVisibility((View.VISIBLE));
                    v2.setVisibility(View.INVISIBLE);
                    v3.setVisibility(View.INVISIBLE);
                    break;
                case R.id.textView2 :
                    v1.setVisibility((View.INVISIBLE));
                    v2.setVisibility(View.VISIBLE);
                    v3.setVisibility(View.INVISIBLE);
                    break;
                case R.id.textView3 :
                    v1.setVisibility((View.VISIBLE));
                    v2.setVisibility(View.VISIBLE);
                    v3.setVisibility(View.VISIBLE);
                    break;
            };

            return true; // 추가 이벤트 처리 여부. 있으면 false 받고 끝내면 true
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);

        TextView tv = (TextView)findViewById(R.id.textView4);

        // 1. R.java 로 가져오기
        tv.setText(R.string.app_company);

        // 2. Resources 클래스 사용하기
        Resources res = getResources(); // 리소스 관리하는 객체
        String company = res.getString(R.string.app_company);
        tv.setText(company);




//        setContentView(R.layout.activity_main);
//
//        v1 = findViewById(R.id.view1);
//        v2 = findViewById(R.id.view2);
//        v3 = findViewById(R.id.view3);
//
//        findViewById(R.id.textView).setOnTouchListener(handler);
//        findViewById(R.id.textView2).setOnTouchListener(handler);
//        findViewById(R.id.textView3).setOnTouchListener(handler);
    }
}
