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
        Log.v(TAG, "cnt :" + cnt);
    }
}
