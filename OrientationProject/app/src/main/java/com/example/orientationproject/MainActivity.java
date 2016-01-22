package com.example.orientationproject;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    EditText et;
    int cnt = 0;

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

    void doAction1(){
        cnt++;
        et.setText(cnt+"");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) { // 폰 환경이 달라지면 잡는거. 화면 전환 같은거
        switch (newConfig.orientation){
            case Configuration.ORIENTATION_LANDSCAPE:
                et.setText("가로모드");
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                et.setText("세로모드");
                break;
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(handler);
    }
}
