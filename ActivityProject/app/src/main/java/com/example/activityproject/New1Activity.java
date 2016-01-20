package com.example.activityproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class New1Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_new1);

        et = (EditText)findViewById(R.id.editText2);
        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button7).setOnClickListener(handler);

        Intent intent = getIntent(); // 이 액티비티가 Intent로 띄어진것이기 때문에 getIntent()로 받는다.


        String name = intent.getStringExtra("name");
        int cnt = intent.getIntExtra("cnt", 100);
        et.setText(name + cnt);

    }
}
