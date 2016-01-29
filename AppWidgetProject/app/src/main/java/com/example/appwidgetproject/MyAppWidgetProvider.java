package com.example.appwidgetproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "Main";
    int cnt = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        if("com.example.appwidgetproject.COUNT".equals(action)){
            doProcess(context, intent);
        }
    }

    void doProcess(Context context, Intent intent){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget_provider);
        views.setTextViewText(R.id.textView, "cnt : " + intent.getStringExtra("cnt"));


        ComponentName componentName = new ComponentName(context, MyAppWidgetProvider.class);

        appWidgetManager.updateAppWidget(componentName, views); // UI 바뀌주는 메소드 updateAppWidget()
    };

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Log.v(TAG, "updateAppWidget : " + appWidgetId);
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget_provider);
        views.setTextViewText(R.id.textView, widgetText);

        Intent intent = new Intent("com.example.appwidgetproject.COUNT"); // 펜딩에 걸 인텐트
        intent.putExtra("cnt", "123");
        // PendingIntent.FLAG_CANCEL_CURRENT 현재 액티비티 종료
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);// intent 방송을 뿌린다.
        views.setOnClickPendingIntent(R.id.button, pendingIntent); // 버튼 클릭시 펜딩 인텐트


        Intent intent1 = new Intent(context, MyService.class); // 서비스 인텐트
        PendingIntent pendingIntent1 = PendingIntent.getService(context, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT); // 서비스를 펜딩 인텐트에
        views.setOnClickPendingIntent(R.id.button2, pendingIntent1);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        cnt++;
        Log.v(TAG, "onUpdate cnt : "+cnt);

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        cnt++;
        Log.v(TAG, "onEnabled cnt : "+cnt);
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.v(TAG, "onDeleted cnt : "+cnt);
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        cnt++;
        Log.v(TAG, "onDisabled cnt : "+cnt);
        // Enter relevant functionality for when the last widget is disabled
    }
}

