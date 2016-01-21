package com.example.fragmentproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    EditText et;
    FirstFragment firstFragment; // 프래그먼트에 접근하기 위해서 선언
    SecondFragment secondFragment; // 프래그먼트에 접근하기 위해서 선언

    Fragment fragment;

    // 핸들러에 넣어주기 위해 멤버변수 밖으로 뺌
    FragmentManager manager; // 어디것을 가져오는지 중요 FragmentManager은 두개가 있다. 우린 support.v4 사용
    FragmentTransaction ft;

    // 방법1. 플레그먼트에서 메인에 접근하기
    public void doChangeData(String data){
        et.setText(data);
    }


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

    int cnt = 0;

    void doAction1(){
        fragment = manager.findFragmentByTag("first");

        if(fragment == null){ // 불필요한 생성 막기
            Log.v(TAG,"first");

            cnt++;
            Bundle bundle = new Bundle(); // 번들 한개 만듬
            bundle.putInt("cnt", cnt);
            bundle.putString("name", "android");

            firstFragment = new FirstFragment();

            firstFragment.setArguments(bundle); // 만든 번들을 넣어줌

            ft = manager.beginTransaction();
            ft.replace(R.id.main, firstFragment, "first"); // main위에다 firstFragment로 대체한다. 3번째 인자는 변수명
            ft.commit();
        }
    }


    void doAction2(){
        fragment = manager.findFragmentByTag("second");

        if(fragment == null) {
            secondFragment = new SecondFragment();
            ft = manager.beginTransaction();
            ft.replace(R.id.main, secondFragment, "second"); // main위에다 secondFragment 대체한다.
            ft.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main1);

        et = (EditText)findViewById(R.id.editText3);

        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);

        // 만든 프래그먼트에 접근하기
        manager = getSupportFragmentManager(); // 어디것을 가져오는지 중요 FragmentManager은 두개가 있다. 우린 support.v4 사용
//        firstFragment = (FirstFragment)manager.findFragmentById(R.id.fragment); // 캐스팅 필요

        if(savedInstanceState == null){ // 불 필요 생성 안하게
            cnt++;
            Bundle bundle = new Bundle(); // 번들 한개 만듬
            bundle.putInt("cnt", cnt);
            bundle.putString("name", "android");
            // 동적 생성
            firstFragment = new FirstFragment();

            firstFragment.setArguments(bundle); // 만든 번들을 넣어줌
//            firstFragment.setData("test", cnt); // 번들 대신 방법

            // 프레그먼트에서 메인으로 데이터 넘기기 방법2. doChangeData 하는거
            firstFragment.setOnMyListener(new FirstFragment.MyListener() { // FirstFragment에서 setOnMyListener를 구현하고 여기서 사용한다.
                @Override
                public void receiveMessage(String data) {
                    doChangeData(data);
                }
            });


            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.main, firstFragment); // 있는거 위에 올린다.
            ft.commit();
        }
    }
}
