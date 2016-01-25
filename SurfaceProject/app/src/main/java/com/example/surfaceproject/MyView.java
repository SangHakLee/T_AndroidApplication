package com.example.surfaceproject;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by skplanet on 2016-01-25.
 * SurfaceView 만들기
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback{
    // 생성자 3개 필요
    // SurfaceHolder.Callback implements 해야얌
    private static final String TAG = "Main";

    SurfaceHolder holder;

    // SurfaceHolder.Callback 오버라이딩 해야할것들
    // 여러 화면 보여주는게 Surface 목적
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        onAir = true;
        new AniThread().start(); // 쓰레드 시작
        Log.v(TAG, "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.v(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(TAG, "surfaceDestroyed");
        onAir = false; // 쓰레드가 멈춘다.
    }

    boolean onAir = false;
    int cnt = 0;
    class AniThread extends Thread{
        public void run(){
            while(onAir){
                cnt++;
                Log.v(TAG, "cnt : "+ cnt);
                SystemClock.sleep(1000);
            }
        }
    }

    Context context;

    // MyView 생성자
    public void init(Context context){
        this.context = context;

        holder = getHolder();
        holder.addCallback(this); // this 로 자기 자신의 인터페이스 변화 확인.
    }

    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
