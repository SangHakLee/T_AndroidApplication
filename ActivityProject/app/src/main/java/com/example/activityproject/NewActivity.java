package com.example.activityproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class NewActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    EditText et;
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button :
                    doAction();
                    break;
                case R.id.button7 :
                    doAction2();
                    break;
                case R.id.button9 :
                    doAction3();
                    break;
                case R.id.button10 :
                    doAction4();
                    break;
            }
        }
    };

    void doAction(){
        Intent data = new Intent();
        data.putExtra("result", et.getText().toString());
        data.putExtra("tel", "000000000");

        setResult(RESULT_OK, data); // resultCode
        finish(); // 반드시 써야한다.
    }

    void doAction2(){
        setResult(123); // resultCode   ok : -1  cancel : 0
        finish(); // 반드시 써야한다.
    }

    int cnt = 0;
    void doAction3(){
        cnt++;
        et.setText("aaa");
    }
    void doAction4(){
        Intent intent = new Intent(this, NewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 뷰 재활용
        intent.putExtra("name", et.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.v(TAG, "onNewIntent" + intent.getStringExtra("name"));
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        et = (EditText)findViewById(R.id.editText2);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button7).setOnClickListener(handler);
        findViewById(R.id.button9).setOnClickListener(handler);
        findViewById(R.id.button10).setOnClickListener(handler);

        Intent intent = getIntent(); // 이 액티비티가 Intent로 띄어진것이기 때문에 getIntent()로 받는다.

        // 기존 방법
//        String name = intent.getStringExtra("name"); // name으로 넘어온것 받는다.
//        int cnt = intent.getIntExtra("cnt", 100);
//        et.setText(name + cnt);


        // 직렬화 방법
//        MyItem item =  (MyItem)intent.getSerializableExtra("item"); // MyItem으로 캐스팅 해야함
//        if( item != null) {
//            et.setText(item.name + item.cnt);
//        }else{
//            et.setText("No value");
//        }

        // parcer 방법
//        MyItem1 item1 = intent.getParcelableExtra("item1");
//        if( item1 != null) {
//            et.setText(item1.name + item1.cnt);
//        }else{
//            et.setText("No value");
//        }

        String name = intent.getStringExtra("name");
        int cnt = intent.getIntExtra("cnt", 100);
        et.setText(name + cnt);

    }
}
