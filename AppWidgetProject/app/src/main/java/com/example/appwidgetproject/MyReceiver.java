package com.example.appwidgetproject;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 매니페스트에
        // <intent-filter>
        // <action android:name="com.example.appwidgetproject.COUNT" />
        // </intent-filter>

        String action = intent.getAction();
        if("com.example.appwidgetproject.COUNT".equals(action)){
            doCountProccess(context, intent);
        }else if("com.example.appwidgetproject.START".equals(action)){
            doStartService(context);
        }else if("com.example.appwidgetproject.STOP".equals(action)){
            doStopService(context);
        }
    }

    void doStopService(Context context){
        Intent service = new Intent(context, MyService.class);
        context.stopService(service);
    }

    void doStartService(Context context){
        Intent service = new Intent(context, MyService.class);
        context.startService(service); // 서비스 시작
    }

    // 이 메소드에서 수정
    void doCountProccess(Context context, Intent intent){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        // UI 변경 하는 코드
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget_provider);
        views.setTextViewText(R.id.textView, intent.getStringExtra("cnt"));


        ComponentName componentName = new ComponentName(context, MyAppWidgetProvider.class);

        appWidgetManager.updateAppWidget(componentName, views); // UI 바뀌주는 메소드 updateAppWidget()
    }
}
