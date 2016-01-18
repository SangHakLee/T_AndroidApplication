package com.example.aproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

               case R.id.button2: // 새로 추가된 버튼
                   doAction2();
                   break;

               case R.id.button3: // 새로 추가된 버튼
                   doAction3();
                   break;

               case R.id.button4: // 새로 추가된 버튼
                   doAction4();
                   break;

               case R.id.button5: // 새로 추가된 버튼
                   doAction5();
                   break;

               case R.id.button6: // 새로 추가된 버튼
//                   doAction6();
                   break;

               case R.id.button8: // 새로 추가된 버튼
                   doAction8();
                   break;
           }
       }
   };

    int cnt = 0; // doAction()을 위한 임시 변수
    void doAction1(){
        cnt++;
//        editText.setText(cnt); //에러 남 R.java 거를 가져오기 때문에
        editText.setText(cnt + ""); // 이렇게 해야 String으로 캐스팅해야함.
    };

    void doAction2(){
        Toast.makeText(this, "토스트", Toast.LENGTH_SHORT).show(); // 그냥 this인 이유는 doAction2()가 밖으로 나와서 this가  MainActivity이기 때문에

    };

    void doAction3(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        try{
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this, "오류", Toast.LENGTH_SHORT).show();
        }
    };


    void doAction4(){

    };

    void doAction5(){

    };

    public void doAction6(View v){ // 무조건 public void, 반드시 View 필요
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://114"));
        startActivity(intent);
    }

    void doAction8(){
        Intent intent = new Intent(this, SubActivity.class); //SubActivity.class 가 SubActivity 뛰움
        try{
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this, "오류", Toast.LENGTH_SHORT).show();
        }
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

//        findViewById(R.id.button2).setOnClickListener(handler);// button2는 sub1.xml에 있는 것이기 때문에 앱 실행하자마자 죽는다.

        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);
        findViewById(R.id.button8).setOnClickListener(handler);

    }
}
