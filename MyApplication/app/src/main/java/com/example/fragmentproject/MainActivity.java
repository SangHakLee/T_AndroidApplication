package com.example.fragmentproject;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    FirstFragment firstFragment; // 프래그먼트에 접근하기 위해서 선언
    SecondFragment secondFragment; // 프래그먼트에 접근하기 위해서 선언

    // 핸들러에 넣어주기 위해 멤버변수 밖으로 뺌
    FragmentManager manager = getSupportFragmentManager(); // 어디것을 가져오는지 중요 FragmentManager은 두개가 있다. 우린 support.v4 사용
    FragmentTransaction ft = manager.beginTransaction();


    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button2 :
                    doAction1();
                    break;
                case R.id.button4 :
                    doAction2();
                    break;
            }
        }
    };

    void doAction1(){
        firstFragment = new FirstFragment();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main, firstFragment); // main위에다 firstFragment로 대체한다.
        ft.commit();
    }

    void doAction2(){
        secondFragment = new SecondFragment();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.main, secondFragment); // main위에다 secondFragment 대체한다.
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main1);

        et = (EditText)findViewById(R.id.editText2);

        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);

        // 만든 프래그먼트에 접근하기
        FragmentManager manager = getSupportFragmentManager(); // 어디것을 가져오는지 중요 FragmentManager은 두개가 있다. 우린 support.v4 사용
//        firstFragment = (FirstFragment)manager.findFragmentById(R.id.fragment); // 캐스팅 필요

        if(savedInstanceState == null){ // 불 필요 생성 안하게

            // 동적 생성
            firstFragment = new FirstFragment();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.main, firstFragment); // 있는거 위에 올린다.
            ft.commit();
        }
    }
}
