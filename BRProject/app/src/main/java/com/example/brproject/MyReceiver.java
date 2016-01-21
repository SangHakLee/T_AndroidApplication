package com.example.brproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String  TAG = "Main";
    int cnt = 0;

    @Override
    public void onReceive(Context context, Intent intent) { // 방송 듣는 부분
        cnt++;
        String action = intent.getAction(); // 어떤 방송인지 분기하기 위해서
        Log.v(TAG, "cnt :" + cnt);
        Log.v(TAG, "action :" + action);
    }
}
