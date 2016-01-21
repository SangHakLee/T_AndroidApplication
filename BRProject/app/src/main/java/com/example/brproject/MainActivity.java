package com.example.brproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String  TAG = "Main";

    EditText et;

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(receiver); // 리시버 멈춤
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(); // 클래스 객체 선언
        filter.addAction("com.example.brproject.intent.action.VIEW");
        filter.addAction("com.example.brproject.intent.action.DELETE");
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);

        registerReceiver(receiver, filter);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() { // 이 리시버는 바로 동작하지 않는다. 등록해야함
        @Override
        public void onReceive(Context context, Intent intent) {
            et.setText(intent.getAction());
        }
    };

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button :
                    doAction1();
                    break;

                case R.id.button2 :
                    doAction2();
                    break;

                case R.id.button3 :
                    doAction3();
                    break;
            }
        }
    };

    void doAction3(){
        Intent intent = new Intent("com.example.brproject.intent.action.DELETE"); // 권장방법 : 패키지명.intent.action.~~~
        intent.putExtra("name", "sam.txt");
        sendBroadcast(intent); // 방송 날리기
    }

    void doAction1(){
        Intent intent = new Intent("com.example.brproject.intent.action.VIEW"); // 권장방법 : 패키지명.intent.action.~~~
        intent.putExtra("name", "sam.txt");
        sendBroadcast(intent); // 방송 날리기
    }

    void doAction2(){
        Intent intent = new Intent("com.example.brproject.intent.action.LOAD"); // 권장방법 : 패키지명.intent.action.~~~
        intent.putExtra("name", "sam.txt");
        sendBroadcast(intent); // 방송 날리기
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);

        et = (EditText)findViewById(R.id.editText);
    }
}
