package com.example.serviceproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "Main";
    int cnt = 0;

    boolean onAir = false;

    // 서비스에서는 대부분 쓰레드가 만들어진다.
    class JobThread extends Thread{ // 쓰레드 생성
        public void run(){
//            Intent intent = new Intent(); // 이렇게 하면 안됨. 매번 생성해야한다.
            Intent intent = null;
            while( onAir ){
                cnt ++;
                if( cnt %3 == 0 ){ // 특정 조건이 맞는다면
                    intent = new Intent("com.example.serviceproject.intent.action.COUNT"); // 매번 새로운 Intent 만들어야한다!!. 이름은 권장사항
                    intent.putExtra("cnt", cnt); // Intent를 통해 cnt를 보낸다.
                    sendBroadcast(intent);
                }
                Log.v(TAG, "cnt : " + cnt);
                SystemClock.sleep(300); // java sleep 대체
            }
        }
    }

    public MyService() {
    }

    JobThread trd = null; // 쓰레드

    @Override
    public void onCreate() {
        cnt++;
        Log.v(TAG, "onCreate : " + cnt);

        onAir = true; // 쓰레드 run 하게 하는 플래그
        trd = new JobThread(); // 쓰레드 생성
        trd.start();// 쓰레드 시작

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        cnt++;
        Log.v(TAG, "onDestroy : " + cnt);
        onAir = false; // 쓰레드 종료하기 위한 플래그
//        trd.interrupt(); // 좋은 방법이 아님 !!!

        try {
            trd.join(); // 서비스가 종료되어도 쓰레드가 남아있는 메모리 누수를 방지하기 위해서 join()을 걸어서 쓰레드가 끝날때까지 기다린다.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        cnt++;
        Log.v(TAG, "onStartCommand : " + cnt + "name : " + name);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
