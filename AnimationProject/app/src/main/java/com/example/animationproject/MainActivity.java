package com.example.animationproject;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    AnimationDrawable animationDrawable; // 애니메이션
    TextView tv;

    Animation animation;
    Animation buttonAnimation;
    ValueAnimator valueAnimator;

    TextSwitcher textSwitcher;

    ImageView img1;

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
                case R.id.button4:
                    doAction4();
                    break;
                case R.id.button5:
                    doAction5();
                    break;
                case R.id.button6:
                    doAction6();
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

    void doAction4(){
        // xml로 하는거
//        valueAnimator.start();

        // java로 하기
        ObjectAnimator animator = ObjectAnimator.ofInt(tv, "backgroundColor", Color.BLACK, Color.RED);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    void doAction5(){
        textSwitcher.showNext();
    }

    void doAction6(){
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable)img1.getDrawable();
        drawable.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Log.v("Main", hasFocus + "");

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
        findViewById(R.id.button4).setOnClickListener(handler);

        img = (ImageView)findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.textView2);

        animationDrawable = (AnimationDrawable)img.getDrawable(); // 애니메이션 가져오기

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.move_image);
        buttonAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button_animation);

        // xml로 하기
//        valueAnimator =  (ValueAnimator)AnimatorInflater.loadAnimator(this, R.animator.color_animator); // xml에 있는거 가져오기
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int color = (Integer)animation.getAnimatedValue();
//                tv.setBackgroundColor(color);
//            }
//        });


        textSwitcher = (TextSwitcher)findViewById(R.id.textSwitcher);
        findViewById(R.id.button5).setOnClickListener(handler);


        findViewById(R.id.button6).setOnClickListener(handler);
        img1 = (ImageView)findViewById(R.id.imageView2);


    }
}
