package com.example.animationproject;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    AnimationDrawable animationDrawable;

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

    void doAction1(){
        animationDrawable.start(); // 애니메이션 시작
    }

    void doAction2(){
        animationDrawable.stop(); // 애니메이션 종료
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Log.v("Main", hasFocus+ "");

        if(hasFocus){
            animationDrawable.stop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);

        img = (ImageView)findViewById(R.id.imageView);

        animationDrawable = (AnimationDrawable)img.getDrawable(); // 애니메이션 가져오기

    }
}
