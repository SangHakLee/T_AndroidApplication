package com.example.webproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    EditText et;
    WebView view;

    WebSettings settings; // 웹 뷰 설정

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
//                    doAction1();
                    break;
                case R.id.button2:
//                    doAction2();
                    break;
                case R.id.button3:
//                    doAction3();
                    break;
                case R.id.button4:
//                    doAction4();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText);

        et.setText("http://m.daum.net");
        view = (WebView)findViewById(R.id.webView);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);

        settings = view.getSettings(); // 웹 뷰 설정 가져옴
        settings.setJavaScriptEnabled(true); // 자바스크립트 사용

        view.setWebViewClient(new MyWebViewClient()); // 내가 만든 WebViewClient 사용
        view.loadUrl("http://m.naver.com"); // 처음 앱 구동시 뜨는 페이지

    }

    class MyWebViewClient extends WebViewClient{ // 웹에서 a태그 클릭시 크롬으로 가는걸 막기위해서

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { // a태그 링크 눌리면 실행
            view.loadUrl(url); // 1. 주소값
            return true;
        }
    }
}
