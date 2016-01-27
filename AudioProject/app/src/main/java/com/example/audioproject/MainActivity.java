package com.example.audioproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
            }
        }
    };

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
        Log.v(TAG, "path : " + path);
        try {
            mediaPlayer2.setDataSource(path);

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

        mediaPlayer1 = MediaPlayer.create(this, R.raw.dingdong); // 짧은 음원은 그냥 하면 끝

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
