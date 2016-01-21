package com.example.fragmentproject;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    FirstFragment firstFragment; // 프래그먼트에 접근하기 위해서 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.editText2);

        // 만든 프래그먼트에 접근하기
        FragmentManager manager = getSupportFragmentManager(); // 어디것을 가져오는지 중요 FragmentManager은 두개가 있다. 우린 support.v4 사용
        firstFragment = (FirstFragment)manager.findFragmentById(R.id.fragment); // 캐스팅 필요
    }
}
