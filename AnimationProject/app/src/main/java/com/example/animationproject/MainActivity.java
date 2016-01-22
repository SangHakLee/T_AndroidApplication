package com.example.animationproject;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    AnimationDrawable animationDrawable; // 애니메이션
    Animation animation;
    Animation buttonAnimation;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.startAnimation(buttonAnimation); // 현재 눌린 버튼에 애니메이션 효과를 준다.
            switch (v.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button2:
                    doAction2();
                    break;
                case R.id.button3:
                    doAction3();
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

    boolean toggle = true;
    void doAction3(){
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move_image);
        if(toggle){
            img.startAnimation(animation);
            toggle = false;
        }else{
            img.clearAnimation();
            toggle = true;
        }
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
        findViewById(R.id.button3).setOnClickListener(handler);

        img = (ImageView)findViewById(R.id.imageView);

        animationDrawable = (AnimationDrawable)img.getDrawable(); // 애니메이션 가져오기

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move_image);
        buttonAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button_animation);

    }
}
