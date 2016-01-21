package com.example.serviceproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    EditText et;

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
                    break;

                case R.id.button4:
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
