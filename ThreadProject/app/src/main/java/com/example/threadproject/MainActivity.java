package com.example.threadproject;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    EditText et;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1();
                    break;
            }
        }
    };

    boolean onAir = false;

    class AnyThread extends Thread{
        public void run(){
            while(onAir){
                cnt++;
                Log.v(TAG, "cnt : "+ cnt);
//                et.setText("cnt : " + cnt); // 내가 만든 쓰레드는 UI 변경 불가. 메인 쓰레드만 가능하다. Toast도 마찬가지 불가능
                SystemClock.sleep(300);
            }
        }
    }

    int cnt = 0;

    void doAction1(){
        // 시작
        cnt++;
        et.setText("cnt : " + cnt);
        onAir = true;
        new AnyThread().start(); // 쓰레드 실행
        // 끝 5초 이내 완료가 필수
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(handler);
    }
}
