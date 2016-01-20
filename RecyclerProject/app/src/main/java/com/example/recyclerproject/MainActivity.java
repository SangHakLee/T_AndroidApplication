package com.example.recyclerproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView view;
    ArrayList<MyItem> data;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (RecyclerView)findViewById(R.id.view);
        data = new ArrayList<MyItem>();// 데이터 생성하는 부분.없으면 null point
        initData();
        adapter = new MyAdapter(this, R.layout.item, data);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        view.setLayoutManager(manager);

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

    }
}
