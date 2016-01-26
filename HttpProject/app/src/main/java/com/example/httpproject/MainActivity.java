package com.example.httpproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    EditText et;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1();
            }
        }
    };

    // UI 바꾸기 핸들러 만들기
    Handler handler = new Handler(){
        // handleMessage 재정의
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 999:
                    Toast.makeText(MainActivity.this, msg.arg1 + "코드 에러", Toast.LENGTH_SHORT).show();
            }
        }
    };

    // 쓰레드 생성 해서 여기서 네트워크 연결
    class NetworkThread extends Thread{
        public void run(){
            String stringUrl = "http://www.naver.com"; // 접속할 주소

            URL url = null;
            HttpURLConnection connection = null; //좀더 다양한 처리 가능
            int code;
            // 네트워크는 꼭 try catch
            try {
                url = new URL(stringUrl);
                connection = (HttpURLConnection)url.openConnection(); // 커넥션
                code = connection.getResponseCode(); // 응답 코드
                Log.v(TAG, "code : "+ code);

                Message msg = handler.obtainMessage();
                switch (code){
                    case HttpURLConnection.HTTP_OK :
                        // 여기서 UI 바꾸기 안됨
                        // 핸들러 이용
                        break;

                    default:
                        msg.what = 999;
                        msg.arg1 = code;
                        break;
                }

                // UI 변경을 위해서 핸들러에게 메시지 보낸다.
                handler.sendMessage(msg);
                
            } catch (IOException e) {
//            e.printStackTrace();
                Log.v(TAG, "error : " + e);
            }finally {
                connection.disconnect();
            }
        }
    }

    // 네트워크로 서버통신
    public void doAction1(){
        new NetworkThread().start();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);

        et = (EditText)findViewById(R.id.editText);


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
