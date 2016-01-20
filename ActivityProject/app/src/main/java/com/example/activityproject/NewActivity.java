package com.example.activityproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        et = (EditText)findViewById(R.id.editText2);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button7).setOnClickListener(handler);

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
