package com.example.list1project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView view;
    ArrayAdapter<String> adapter = null;
    String[] data;

    EditText editText;
    ArrayList<String> list = new ArrayList<>();
    View.OnClickListener handler = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            doAction1();
        }
    };

    void doAction1(){
        String msg = editText.getText().toString(); // 반환타입 때문에 캐스팅
        if(msg != null && msg.length() > 0){
            adapter.add(msg);
            editText.setText("");
            view.smoothScrollToPosition(adapter.getCount()-1); // 0-base 이기 때문에 -1
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = getResources().getStringArray(R.array.myArr); // 이건 배열 그냥 하면 에러
        for(String s : data){ // 어레이 리스트에 넣고
            list.add(s);
        }
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data); // 아직 만든게 없기 때문에 기본 안드로이드 android.R.layout.simple_list_item_1 사용
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list); // 어레이리스트를 넣는다.
        view = (ListView) findViewById(R.id.listView); // 뷰에 리스트뷰 연결
        view.setAdapter(adapter); // 어답터로 뷰를 그림

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() { // OnItemClickListener 재정의
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                //getItemAtPosition 의 반환은 Object이기 때문에 String으로 캐스팅 해야한다.
                Toast.makeText(MainActivity.this, (String)view.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });


        editText = (EditText)findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(handler);
    }
}
