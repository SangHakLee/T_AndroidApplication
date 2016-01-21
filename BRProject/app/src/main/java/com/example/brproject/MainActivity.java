package com.example.brproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String  TAG = "Main";


    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button :
                    doAction1();
                    break;

                case R.id.button2 :
//                    doAction2();
                    break;

                case R.id.button3 :
//                    doAction3();
                    break;
            }
        }
    };

    void doAction1(){
        Intent intent = new Intent("com.example.brproject.intent.action.VIEW"); // 권장방법 : 패키지명.intent.action.~~~
        intent.putExtra("name", "sam.txt");
        sendBroadcast(intent); // 방송 날리기
        Log.v(TAG, "Ok");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
    }
}
