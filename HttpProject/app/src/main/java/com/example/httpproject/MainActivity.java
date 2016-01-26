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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    Handler uiHandler = new Handler(){
        // handleMessage 재정의
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 999:
                    Toast.makeText(MainActivity.this, msg.arg1 + "코드 에러", Toast.LENGTH_SHORT).show();
                    break;

                case 800:
                    et.setText(msg.obj.toString()); // 읽어들은 값을 텍스트 뷰에 뿌린다.
            }
        }
    };

    // 쓰레드 생성 해서 여기서 네트워크 연결
    class NetworkThread extends Thread{
        public void run(){
            String stringUrl = "http://m.google.co.kr";; // 접속할 주소

            URL url = null;
            HttpURLConnection connection = null; //좀더 다양한 처리 가능
            int code;
            // 네트워크는 꼭 try catch
            try {
                url = new URL(stringUrl);
                connection = (HttpURLConnection)url.openConnection(); // 커넥션
//                connection.setDoOutput(true); // 이 코드가 있으면 POST 방식
                connection.setConnectTimeout(10000); // 10초 동안 기다림 10초 넘으면 exception
                connection.setReadTimeout(10000); // 10초 동안 기다림 10초 넘으면 exception
                code = connection.getResponseCode(); // 응답 코드
                Log.v(TAG, "code : "+ code);

                Message msg = uiHandler.obtainMessage();
                switch (code){
                    case HttpURLConnection.HTTP_OK :
                        // 여기서 UI 바꾸기 안됨
                        // 핸들러 이용
                        String data = getData(new BufferedReader(new InputStreamReader(connection.getInputStream())) ); // 내가 만든 메소드 connection.getInputStream() : 입력용 객체
                        msg.what = 800;
                        msg.obj = data;
                        break;

                    default:
                        msg.what = 999;
                        msg.arg1 = code;
                        break;
                }

                // UI 변경을 위해서 핸들러에게 메시지 보낸다.
                uiHandler.sendMessage(msg);

            } catch (IOException e) {
//            e.printStackTrace();
                Log.v(TAG, "error : " + e);
            }finally {
                connection.disconnect();
            }
        }
    }

    // connection.getInputStream() 로 읽어온 데이터 br
    String getData(BufferedReader br){
        String data = "";
        String readData = ""; // 읽은 데이터
        StringBuilder builder = new StringBuilder();
        try {
            while ( (readData = br.readLine()) != null ){
                builder.append(readData).append(" \n");
            }
            data = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    };

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
