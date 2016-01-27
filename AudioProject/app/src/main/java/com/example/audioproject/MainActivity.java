package com.example.audioproject;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.IOException;

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
                    doAction2("music.mp3");
                    break;
                case R.id.button3:
                    doAction3();
                    break;
                case R.id.button4:
                    doAction4();
                    break;
                case R.id.button5:
                    doAction5("hoot.mp4"); //영상 재생
                    break;
                case R.id.button6:
                    doAction6(); // 영상 멈춤
                    break;
            }
        }
    };

    MediaPlayer mediaPlayer3;
    SurfaceView view;
    SurfaceHolder holder;
    SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    // 동영상 정지
    public void doAction6(){
        if(mediaPlayer3.isPlaying()){
            position = mediaPlayer3.getCurrentPosition(); // 현재 재생 위치 저장
            mediaPlayer3.stop();
        }
    }
    // 동영상 재생
    public void doAction5(String fName){
        if(mediaPlayer3 == null){
            mediaPlayer3 = new MediaPlayer();
        }

        if(mediaPlayer3.isPlaying()){
            mediaPlayer3.stop();
            mediaPlayer3.reset(); // 초기화 작업
        }
        String path = "";
        File sdCard = Environment.getExternalStorageDirectory();
        File file = new File(sdCard, fName);

        Uri uri = Uri.fromFile(file);
        Log.v(TAG, "play uri : "+ uri);



        try {


            mediaPlayer3.setDataSource(this, uri); // 인터넷에 있는거
//            mediaPlayer3.setDataSource(path); // 인터넷에 있는거
            mediaPlayer3.prepare(); //블럭됨 쓰레드에서 해야함
            mediaPlayer3.setDisplay(holder); // 보여주고자 하는 것
            mediaPlayer3.start(); // prepare()와 같이 씀

//            mediaPlayer3.setOnPreparedListener(preparedListener); // 위에서 만든 OnPreparedListener 에 리스너 건다.
//            mediaPlayer3.setOnSeekCompleteListener(seekCompleteListener);
//            mediaPlayer3.prepareAsync(); // 동기 쓰레드 필요없음

        } catch (IOException e) {
            Log.v(TAG, "play error : "+ e);
        }
    }


    public void doAction4(){
        if(position > 0){
            mediaPlayer2.seekTo(position); // 재생 위치부터 시작
        }
//        mediaPlayer2.start();
    }

    int position;
    // 영상 정지
    public void doAction3(){
        if(mediaPlayer2.isPlaying()){
            position = mediaPlayer2.getCurrentPosition(); // 현재 재생 위치 저장
            mediaPlayer2.pause();
        }
    }

    // 액티비티 종료 될때
    @Override
    protected void onDestroy() {
        killMediaPlaying(); // 액티비티 안에서만 재생 하려고
        super.onDestroy();
    }

    public void killMediaPlaying(){
        if(mediaPlayer2 != null){
            if(mediaPlayer2.isPlaying()){
                mediaPlayer2.stop();
            }
            mediaPlayer2.release(); // 꼭 할것
            mediaPlayer2 = null;
            System.gc();
        }
    }

    MediaPlayer.OnSeekCompleteListener seekCompleteListener = new MediaPlayer.OnSeekCompleteListener(){
        @Override
        public void onSeekComplete(MediaPlayer mp) {
            mp.start();

        }
    };

    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener(){
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start(); // 동기는 여기서 start()
        }
    };

    MediaPlayer mediaPlayer2;
    // 긴 파일 재생
    public void doAction2(String fName){
        if(mediaPlayer2 == null){
            mediaPlayer2 = new MediaPlayer();
        }

        if(mediaPlayer2.isPlaying()){
            mediaPlayer2.stop();
            mediaPlayer2.reset(); // 초기화 작업
        }
        String path = "";
        File sdCard = Environment.getExternalStorageDirectory();
        File file = new File(sdCard, fName);
        path = file.getAbsolutePath();

        String audioUrl = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";
        Uri uri = Uri.parse(audioUrl);

        try {

//            mediaPlayer2.setDataSource(path); // 실제 디바이스에 있는거 쓸 때

            mediaPlayer2.setDataSource(this, uri); // 인터넷에 있는거

//            mediaPlayer2.prepare(); //블럭됨 쓰레드에서 해야함
//            mediaPlayer2.start(); // prepare()와 같이 씀

            mediaPlayer2.setOnPreparedListener(preparedListener); // 위에서 만든 OnPreparedListener 에 리스너 건다.
            mediaPlayer2.setOnSeekCompleteListener(seekCompleteListener);
            mediaPlayer2.prepareAsync(); // 동기 쓰레드 필요없음

        } catch (IOException e) {
            Log.v(TAG, "play error : "+ e);
        }
    }

    MediaPlayer mediaPlayer1;
    // 미디어 플레이어를 통한 재생
    public void doAction1(){
        mediaPlayer1.start(); // 시작
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);
        findViewById(R.id.button6).setOnClickListener(handler);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.dingdong); // 짧은 음원은 그냥 하면 끝

        // SurfaceView 반드시 해야할것
        view = (SurfaceView)findViewById(R.id.surfaceView);
        holder = view.getHolder();
        holder.addCallback(callback); // addCallback 해서 callback을 관리
        // SurfaceView 반드시 해야할것

        if(Build.VERSION.SDK_INT < 11){
            // 최신버전에서 쓰면 안나오기 때문에 if문
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // 예전 폰에서 나오게 하기 위해서
        }

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
