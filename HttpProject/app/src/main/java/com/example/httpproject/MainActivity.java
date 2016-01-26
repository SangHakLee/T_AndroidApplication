package com.example.httpproject;

import android.os.AsyncTask;
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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    EditText et;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction2();
                case R.id.button2:
                    doAction3();

            }
        }
    };

    public void doAction3(){

    };


    // 쓰레드 대신 AsyncTask 사용
    class HttpTask extends AsyncTask<String, Void, String >{
        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0]; // doAction2()에서 인자값
            HttpClient client = null;
            HttpGet request = null; // 요청 객체
            HttpResponse response = null; // 응답 객체

            String data = "";

            // http://openapi.naver.com/search?key=c1b406b32dbbbbeee5f2a36ddc14067f&query=영화&display=10&start=1&target=movie 이거  만들기
            ArrayList<NameValuePair> queryList = new ArrayList<NameValuePair>(); // 쿼리 스트링 만들 리스트
            // 한글은 URLEncoder 꼭 사용
            queryList.add(new BasicNameValuePair("query", "영화"));
            queryList.add(new BasicNameValuePair("key", "c1b406b32dbbbbeee5f2a36ddc14067f"));
            queryList.add(new BasicNameValuePair("display", "10"));
            queryList.add(new BasicNameValuePair("start", "1"));
            queryList.add(new BasicNameValuePair("target", "movie"));

            String query = URLEncodedUtils.format(queryList, "utf-8"); // 사용법
            String url = stringUrl + "?" + query; // 주의사항 url엔 공백 없다.
            Log.v(TAG, "url : " + url);


            int code;
            // 네트워크는 꼭 try catch
            try {
                client = new DefaultHttpClient();
//                request = new HttpGet(stringUrl); // 접속하려는 URL 인자로
                request = new HttpGet(url); // NameValuePair 로 만든 쿼리 스트링 Url
                response = client.execute(request);

                code = response.getStatusLine().getStatusCode(); // 응답객체 응답코드

                Log.v(TAG, "code : "+ code);

                switch (code){
                    case HttpURLConnection.HTTP_OK :
                        data = getData( new BufferedReader(new InputStreamReader(response.getEntity().getContent())) ); //  getEntity() 입출력 관리, getContent() 입력 관리
                        break;

                    default:
                        data = " code : "+ code;
                        break;
                }


            } catch (IOException e) {
//            e.printStackTrace();
                Log.v(TAG, "error : " + e);
            }finally {
            }
            return data; // onPostExecute() 로 데이터 이동
        }

        @Override
        protected void onPostExecute(String s) {
            et.setText(s); // HttpTask 에서 return 값
            super.onPostExecute(s);
        }
    }

    public void doAction2(){
//        new HttpTask().execute("http://m.google.co.kr"); // onPostExecute로 인자값 전달됨;
        new HttpTask().execute("http://openapi.naver.com/search"); // onPostExecute로 인자값 전달됨;
    }
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
        findViewById(R.id.button2).setOnClickListener(handler);

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
