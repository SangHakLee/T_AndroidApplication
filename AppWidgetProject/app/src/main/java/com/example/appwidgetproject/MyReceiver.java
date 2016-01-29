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
        }else if("com.example.appwidgetproject.START.ACTIVITY".equals(action)){
            doStartActivity(context, intent);
        }
    }

    void doStartActivity(Context context, Intent intent){
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1. addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //이거 해야 새로운 태스크가 만들어지고 그 위에 화면을 쌓을 수 있다.
        context.startActivity(intent1);
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

        // 아래 코드들만 실행하면 앱위젯 제어가능
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        // UI 변경 하는 코드
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget_provider);
        views.setTextViewText(R.id.textView, intent.getStringExtra("cnt"));


        ComponentName componentName = new ComponentName(context, MyAppWidgetProvider.class);

        appWidgetManager.updateAppWidget(componentName, views); // UI 바뀌주는 메소드 updateAppWidget()
    }
}
