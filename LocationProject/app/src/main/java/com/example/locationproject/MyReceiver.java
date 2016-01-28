package com.example.locationproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "Main";

    public MyReceiver() {
    }

    // Main에서 보낸 intent가 여기로 들어온다
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean flag = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true); // Main에서 준거 받아오기
        Log.v(TAG, flag ? "영역안에 들어옴" : "영역에서 벗어남");
    }
}
