package com.example.threadproject;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    EditText et, et2;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1();
                    break;

                case R.id.button2:
                doAction2();
                break;
            }
        }
    };

    boolean onAir = false;
    Handler handler1 = new Handler(){//  android.os 에 있는 Handler import
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){ // 같은 핸들러에 what int를 다르게 주었기 때문에 분기 가능
                case 100:
                    et.setText(msg.arg1+""); // 여기서 sendMessage로 받은 메시지를 받아온다.
                    break;

                case 200:
//                    et2.setText(msg.arg1+""); // 여기서 sendMessage로 받은 메시지를 받아온다.
                    et2.setText(cnt+""); // sendEmptyMessage 로 받는 방법

                    break;
            }

        }
    };


    class AnyThread2 extends Thread{
        public void run(){

            Message msg = null; // 여기서 만들면 에러. 재사용 하면 안된다.
            while(onAir){
                cnt++;
                Log.v(TAG, "cnt2 : "+ cnt);

                handler1.sendEmptyMessage(200); // 분기에 사용되는 200 넘김

//                // 핸들러를 이용해서 메세지 보내기
//                msg = handler1.obtainMessage();
//
//                msg.arg1 = cnt;
//                msg.what = 200; // 구분하기 위한 넘버링
//
//                handler1.sendMessage(msg); // handleMessage로 간다.


//                et.setText("cnt : " + cnt); // 내가 만든 쓰레드는 UI 변경 불가. 메인 쓰레드만 가능하다. Toast도 마찬가지 불가능  -> Handler 필요
                SystemClock.sleep(500);
            }
        }
    }

    class AnyThread extends Thread{
        public void run(){

            Message msg = null; // 여기서 만들면 에러. 재사용 하면 안된다.
            while(onAir){
                cnt++;
                Log.v(TAG, "cnt : "+ cnt);

                // 핸들러를 이용해서 메세지 보내기
                msg = handler1.obtainMessage();
                msg.what = 100; // 구분하기 위한 넘버링
                msg.arg1 = cnt;

                handler1.sendMessage(msg); // handleMessage로 간다.


//                et.setText("cnt : " + cnt); // 내가 만든 쓰레드는 UI 변경 불가. 메인 쓰레드만 가능하다. Toast도 마찬가지 불가능  -> Handler 필요
                SystemClock.sleep(300);
            }
        }
    }

    int cnt = 0;

    void doAction2(){
        // 시작
        cnt++;
        et.setText("cnt : " + cnt);
        onAir = true;
        new AnyThread2().start(); // 쓰레드 실행
        // 끝 5초 이내 완료가 필수
    }

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
        et2 = (EditText)findViewById(R.id.editText2);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
    }
}
