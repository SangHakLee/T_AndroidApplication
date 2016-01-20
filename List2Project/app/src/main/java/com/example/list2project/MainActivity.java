package com.example.list2project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView view;
    ArrayList<MyItem> data = new ArrayList<MyItem>(); // 데이터 관리 리스트

    MyAdapter adapter; // MyAdapter Class 먼저 생성해야함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 묵시적 inflate
        view = (ListView)findViewById(R.id.listView);
        initData();// 이거 없어서 난 에러

        View headerView = View.inflate(this, R.layout.title_view, null); // title_view.xml에 내용을 헤더로 넣기위해 inflate 한다.
        view.addHeaderView(headerView); // 헤더 뷰에 추가해준다.

        adapter = new MyAdapter(this, R.layout.item, data); // this가 context임,
        view.setAdapter(adapter);
    }

    void initData(){
        data.add(new MyItem("2,000", R.drawable.icon01, 1000, "멀티탭1"));
        data.add(new MyItem("2,000", R.drawable.icon02, 2000, "멀티탭2"));
        data.add(new MyItem("2,000", R.drawable.icon03, 3000, "멀티탭3"));
        data.add(new MyItem("2,000", R.drawable.icon04, 4000, "멀티탭4"));
        data.add(new MyItem("2,000", R.drawable.icon05, 5000, "멀티탭5"));
        data.add(new MyItem("2,000", R.drawable.icon06, 6000, "멀티탭6"));
        data.add(new MyItem("2,000", R.drawable.icon01, 1000, "멀티탭6"));
        data.add(new MyItem("2,000", R.drawable.icon02, 2000, "멀티탭5"));
        data.add(new MyItem("2,000", R.drawable.icon03, 3000, "멀티탭4"));
        data.add(new MyItem("2,000", R.drawable.icon04, 4000, "멀티탭3"));
        data.add(new MyItem("2,000", R.drawable.icon05, 5000, "멀티탭2"));
        data.add(new MyItem("2,000", R.drawable.icon06, 6000, "멀티탭1"));

        data.add(new MyItem("2,000", R.drawable.icon01, 1000, "멀티탭1"));
        data.add(new MyItem("2,000", R.drawable.icon02, 2000, "멀티탭2"));
        data.add(new MyItem("2,000", R.drawable.icon03, 3000, "멀티탭3"));
        data.add(new MyItem("2,000", R.drawable.icon04, 4000, "멀티탭4"));
        data.add(new MyItem("2,000", R.drawable.icon05, 5000, "멀티탭5"));
        data.add(new MyItem("2,000", R.drawable.icon06, 6000, "멀티탭6"));
        data.add(new MyItem("2,000", R.drawable.icon01, 1000, "멀티탭6"));
        data.add(new MyItem("2,000", R.drawable.icon02, 2000, "멀티탭5"));
        data.add(new MyItem("2,000", R.drawable.icon03, 3000, "멀티탭4"));
        data.add(new MyItem("2,000", R.drawable.icon04, 4000, "멀티탭3"));
        data.add(new MyItem("2,000", R.drawable.icon05, 5000, "멀티탭2"));
        data.add(new MyItem("2,000", R.drawable.icon06, 6000, "멀티탭1"));
    }
}
