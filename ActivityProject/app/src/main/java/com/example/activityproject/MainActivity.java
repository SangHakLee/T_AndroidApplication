package com.example.activityproject;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int[] resId = {R.id.button2, R.id.button3, R.id.button4, R.id.button5}; // 리소스 배열, 여기에 버튼들 배열을 넣는다.
    EditText et1;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button2 :
                    doAction1();
                    break;

                case R.id.button3 :
                    doAction2();
                    break;

                case R.id.button4 :
                    doAction3();
                    break;
                case R.id.button5 :
                    doAction4();
                    break;

            }
        }
    };

    void doAction1(){ // 명시적 Intent
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent); // try catch 할것.
    }

    void doAction2(){ // 묵시적 Intent
        Intent intent = new Intent(Intent.ACTION_SEND); // ACTION_SEND 공유 같은 느낌
        intent.putExtra(Intent.EXTRA_TEXT, "Hello");
        intent.setType("text/plain");
        startActivity(intent); // try catch 할것.
    }

    void doAction3(){ // 다른 앱 실핼 Intent
        Intent intent = new Intent("android.intent.action.MAIN"); // 샐행할 앱의 메니페스트에서  <action android:name="android.intent.action.MAIN" />
        intent.setComponent(new ComponentName("com.example.list2project", "com.example.list2project.MainActivity")); // 1. 다른앱의 패키지 풀네임  2. 메인 액티비티 풀네임
        startActivity(intent); // try catch 할것.
    }

    void doAction4(){ // 명시적 Intent
        Intent intent = new Intent(this, NewActivity.class);
        String name = et1.getText().toString(); // 에디트에 있는 값 가져오기
//        intent.putExtra("name", name); // 그냥 방법
//        intent.putExtra("cnt", 123);

        MyItem item = new MyItem();
        item.name = name;
        item.cnt = 22;
        intent.putExtra("item",item); // 그냥 하면 에러 뜸 Item 클래스에서 Serializable implements 해야함. putExtra는 객체를 못 넘겨서

        startActivity(intent); // try catch 할것.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int id : resId){ // resId에 들어있는것 만큼 이벤트 핸들러를 단다.
            findViewById(id).setOnClickListener(handler);
        }

        et1 = (EditText)findViewById(R.id.editText);
    }
}
