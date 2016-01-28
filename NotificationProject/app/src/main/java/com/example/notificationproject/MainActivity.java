package com.example.notificationproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button2:
                    doAction2();
                    break;
                case R.id.button4:
                    doAction3();
                    break;
            }
        }
    };

    // 커스텀 노티 보내기
    public void doAction3(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon02);

        cnt++;
        Intent intent = new Intent(this, NotiActivity.class);
        intent.setData(Uri.parse("myScheme://"+ getPackageName() + "/"+cnt)); // 다른 인텐트라고 판단시키기위해 항상 다른값을 준다 cnt를 더하면서
        intent.putExtra("cnt", cnt);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 리모트뷰로 커스텀노티 만들기
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.noti); // 1,패키지이름 2.리모트뷰
        views.setTextViewText(R.id.button3, "저장");
        views.setImageViewResource(R.id.imageView,R.drawable.icon05);
        views.setOnClickPendingIntent(R.id.button3, pendingIntent); // 만든 pending intent

        // 노티 기본 패턴
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) // 작은 아이콘, 정적 아이콘 회사 아이콘 같은거
                .setLargeIcon(bitmap) // 비트맵임, 동적 아이콘 쓰는게 좋음
                .setTicker("티커문자열")
                .setContent(views)
                .setAutoCancel(true) // 노티 누르면 없어짐
                .build();

        manager.notify(cnt, notification); // 1.노티 구분 아이디
//        manager.cancel(cnt--); // 특정 노티 지우기 id 번호로 지운다.
    }

    int cnt = 0;

    NotificationManager manager; // 노티 관리 객체
    //노티
    public void doAction2(){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon02);

        cnt++;
        Intent intent = new Intent(this, NotiActivity.class);
        intent.setData(Uri.parse("myScheme://"+ getPackageName() + "/"+cnt)); // 다른 인텐트라고 판단시키기위해 항상 다른값을 준다 cnt를 더하면서
        intent.putExtra("cnt", cnt);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 노티 기본 패턴
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) // 작은 아이콘, 정적 아이콘 회사 아이콘 같은거
                .setLargeIcon(bitmap) // 비트맵임, 동적 아이콘 쓰는게 좋음
                .setTicker("티커문자열")
                .setContentTitle("제목" + cnt)
                .setContentText("내용")
                .setContentInfo("인포") // 시간 밑에 내용
                .setContentIntent(pendingIntent) // 터치하면 실행
                .setAutoCancel(true) // 노티 누르면 없어짐
                .build();

        manager.notify(cnt, notification); // 1.노티 구분 아이디
//        manager.cancel(cnt--); // 특정 노티 지우기 id 번호로 지운다.
    }

    Vibrator vibrator;     // 진동관리 객체
    public void doAction1(){
        vibrator.vibrate(2000); // 2초 동안 진동
        long[] pattern ={1000, 500, 200, 400, 1000}; // 패턴으로 진동이 온다.
        vibrator.vibrate(pattern, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);

        // getSystemService 에서 얻는건 한번만 얻으면 되는거
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE); // 하드웨서 진동 객체 얻기
        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE); // 노티 객체 얻기

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
