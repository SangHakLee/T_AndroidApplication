package com.example.serviceproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "Main";
    int cnt = 0;

    public MyService() {
    }

    @Override
    public void onCreate() {
        cnt++;
        Log.v(TAG, "onCreate : " + cnt);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        cnt++;
        Log.v(TAG, "onCreate : " + cnt);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        cnt++;
        Log.v(TAG, "onCreate : " + cnt + "name : " + name);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
