package com.example.asyncproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    EditText et;
    ProgressBar progressBar;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1();
                    break;
            }
        }
    };

    void doAction1(){
        new JobTask().execute(10, 80, 2); // execute() 안에 인자값은 몇개든 상관없다. ...변수

    }

    ProgressDialog progressDialog;

    // AsyncTask 생성
    class JobTask extends AsyncTask<Integer, Integer, String>{ // 1.doInBackground 인자값, 2.publishProgress 인자값, 3.doInBackground 리턴

        @Override
        protected String doInBackground(Integer... params) { // 쓰레드의 run()과 동일. JobTask().execute()의 인자값이 들어온다.
            int start = params[0];
            int end = params[1];
            int step = params[2];

            int num = start;
            while ( num <= end ){
                // 실제로는 다운로드 같은 작업
                num += step;
                if( num % 10 == 0){
                    // 특정 조건 만족시 UI 변경
                    publishProgress(num, 3, 5); // 이곳의 인자값 타입은 JobTask 클래스의
                    SystemClock.sleep(200);
                }
                return "완료";
            }

            return null;
        }

        // onPreExecute, onPostExecute, onProgressUpdate UI 변경에만 쓸것
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // 사전 UI 변경 작업
            // 다이알로그 띄우기
            Log.v(TAG, "다이얼로그 떠라");
            progressDialog = ProgressDialog.show(MainActivity.this, "", "작업중..."); // 1.context 2.제목 3.메세지
            Log.v(TAG, "다이얼로그 뜸?");

        }

        @Override
        protected void onPostExecute(String s) {
            // s 는 doInBackground의 반환값
            super.onPostExecute(s);

            // 사후 UI 작업. onPreExecute 에서 한거 없애는 용도
            progressDialog.cancel();
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) { // publishProgress 의 인자값이 들어온다.
            et.setText(values[0] + ""); // 에딧텍스트 UI 변경
            progressBar.setProgress(values[0]); // 프로그레스바 변경

            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(handler);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }
}
