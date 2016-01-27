package com.example.audioproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
                    doAction2();
                    break;
            }
        }
    };

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

    MediaPlayer mediaPlayer2;
    // 긴 파일 재생
    public void doAction2(String fNmae){
        if(mediaPlayer2 == null){
            mediaPlayer2 = new MediaPlayer();
        }

        if(mediaPlayer2.isPlaying()){
            mediaPlayer2.stop();
            mediaPlayer2.reset(); // 초기화 작업
        }
        String path = "";
        try {
            mediaPlayer2.setDataSource(path);
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
