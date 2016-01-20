package com.example.activityproject;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int[] resId = {R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button8, R.id.button11}; // 리소스 배열, 여기에 버튼들 배열을 넣는다.
    EditText et1, et2, et3;
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
                case R.id.button6 :
                    doAction5();
                    break;
                case R.id.button8 :
                    doAction6();
                    break;
                case R.id.button11 :
                    doAction7();
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

        // 그냥 방법
//        intent.putExtra("name", name);
//        intent.putExtra("cnt", 123);

        // 직렬화 방법
//        MyItem item = new MyItem();
//        item.name = name;
//        item.cnt = 22;
//        intent.putExtra("item",item); // 그냥 하면 에러 뜸 Item 클래스에서 Serializable implements 해야함. putExtra는 객체를 못 넘겨서

        // parcer 방법
        MyItem1 item1 = new MyItem1(name, 33);
        intent.putExtra("item1", item1);


        startActivity(intent); // try catch 할것.
    }


    void doAction5(){ // 명시적 Intent
        Intent intent = new Intent(this, NewActivity.class);
        String name = et1.getText().toString();
        intent.putExtra("name", name);
        intent.putExtra("cnt", 155);

        MyApplication.title ="tacademy"; // MyApplication에 데이터를 넣는다. 공용으로 사용하기 위헤ㅐ

        startActivityForResult(intent, 100); // 2번째 인자 requestCode
    }

    void doAction6(){ // 명시적 Intent
        Intent intent = new Intent(this, New1Activity.class);
        String name = et1.getText().toString();
        intent.putExtra("name", name);
        intent.putExtra("cnt", 155);

        startActivityForResult(intent, 200); // 2번째 인자 requestCode
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {    // 백그라운드 죽어도 멤버 변수 유지하기 위해서
        outState.putInt("x", x); // 메모리 아웃을 대비해서 저장해둔다. 번들이 있을때까지만. 앱 종료되면 번들도 없어진다.
        super.onSaveInstanceState(outState);
    }

    int x, y;
    void doAction7(){
        x++;
        y++;
        et1.setText(String.format("x : %d y : %d", x, y)); // 멤버 필드가 날아갈수도 있다.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG, requestCode + ", " + resultCode + ", " + data);

        // null point 가 뜰수도 있다 기기의 뒤로 버튼 누르면 그래서 switch 로 잡는다.
        switch(requestCode){
            case RESULT_OK:
                String result = data.getStringExtra("result");
                String tel = data.getStringExtra("tel");
                switch (requestCode){
                    case 100:
                        et2.setText(String.format("result : %s , tel : %s", result, tel));
                        break;

                    case 200:
                        et3.setText(String.format("result : %s , tel : %s", result, tel));
                        break;
                }

                break;

            case RESULT_CANCELED: // 뒤로가기 누르는 경우
                Toast.makeText(this, "취소로 종료됨", Toast.LENGTH_SHORT).show();
                break;

            case 123 : // 앱 종료하는 내가 정의한 코드
                setResult(123);
                finish();
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int id : resId){ // resId에 들어있는것 만큼 이벤트 핸들러를 단다.
            findViewById(id).setOnClickListener(handler);
        }

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText3);
        et3 = (EditText)findViewById(R.id.editText4);

        Log.v(TAG, "Main OnCreate : savedInstanceState : " + savedInstanceState);

        if(savedInstanceState != null){ // 메모리 아웃됬다가 돌아온 경우임
            x = savedInstanceState.getInt("x");
        }
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.v(TAG, "onRestart");
        super.onRestart();
    }


}
