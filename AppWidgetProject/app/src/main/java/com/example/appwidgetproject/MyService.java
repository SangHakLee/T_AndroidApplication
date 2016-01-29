package com.example.appwidgetproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

// 백 그라운드에서 계속 카운팅
// 노래 바뀔때마다 UI 변경처럼 만들기
public class MyService extends Service{
    private static final String TAG = "Main";

    public MyService() {
    }

    int cnt = 0;
    boolean onAir = false;
    class CountThread extends Thread{
        public void run(){
            while (onAir){
                cnt++;
                SystemClock.sleep(1000);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        onAir = true;
        new CountThread().start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onAir = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
