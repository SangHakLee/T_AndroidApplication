package com.example.serviceproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    EditText et;


    BroadcastReceiver receiver = new BroadcastReceiver() { // MyService의 쓰레드에서 보낸 거 받는 리시버
        @Override
        public void onReceive(Context context, Intent intent) {
            et.setText(intent.getIntExtra("cnt",0) + "");
        }
    };

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button:
                    doAction1();
                    break;

                case R.id.button2:
                    doAction2();
                    break;

                case R.id.button3:
                    doAction3();
                    break;

                case R.id.button4:
                    doAction4();
                    break;
            }
        }
    };

    void doAction1(){
        // 서비스 시작 시키는게 Intent
        Intent intent = new Intent(this, MyService.class); // 명시적 Intent, 내가 만든 Intent는 명시적임
        intent.putExtra("name", "android");
        startService(intent); // 서비스 시작
    }

    void doAction2(){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent); // 종료 서비스
    }

    void doAction3(){
        IntentFilter filter = new IntentFilter(); // 수신자
        filter.addAction("com.example.serviceproject.intent.action.COUNT"); // MyService에서 보낸 이름으로 접근
        registerReceiver(receiver, filter); // receiver 는 위에서 선언한 BroadcastReceiver et 값 바꾸는거
    }

    void doAction4(){
        unregisterReceiver(receiver); // 리시버 해제
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
    }
}
