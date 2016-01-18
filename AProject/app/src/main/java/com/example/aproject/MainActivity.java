package com.example.aproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;

   View.OnClickListener handler = new View.OnClickListener(){ // 핸들러 설정
       @Override
       public void onClick(View v) {
           switch (v.getId()){ // 같은 버튼 눌렀을때 인자값으로 분기하기 위해서
               case R.id.btn_send : //전송 버튼
                  textView.setText(editText.getText().toString()); // getText()의 반환값이 String이 아니고 edit이기 때문에 toString() 한다.

                  break;
               case R.id.button: // 새로 추가된 버튼
                   doAction1();
                   break;
           }
       }
   };

    int cnt = 0; // doAction()을 위한 임시 변수
    void doAction1(){
        cnt++;
//        editText.setText(cnt); //에러 남 R.java 거를 가져오기 때문에
        editText.setText(cnt+""); // 이렇게 해야 String으로 캐스팅해야함.
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit_input);
        textView = (TextView)findViewById(R.id.text_message);
        button = (Button)findViewById(R.id.btn_send);

        button.setOnClickListener(handler); // 위에 핸들러를 따로 만들고 넣었다. 내가 쓰는 기본 방식과 다르다

        findViewById(R.id.button).setOnClickListener(handler);// 새로운 버튼

        findViewById(R.id.button2).setOnClickListener(handler);// button2는 sub1.xml에 있는 것이기 때문에 앱 실행하자마자 죽는다.
    }
}
