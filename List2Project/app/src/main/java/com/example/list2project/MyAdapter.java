package com.example.list2project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by skplanet on 2016-01-20.
 */
public class MyAdapter extends BaseAdapter {
    // 반드시 오버라이드 해야하는 메소드가 있다. CTRL + i

    int layout; // 뷰가 필요해서
    ArrayList<MyItem> data; // 데이터 필요
    Context context; // context도 필요하다. 여기선 없기 때문에

    // 생성자 필요
    public MyAdapter(Context context, int layout, ArrayList<MyItem> data ) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size(); // arrayList 키기 반환
    }

    @Override
    public Object getItem(int position) { // 반환 타입이 Object. 받는 부분에서 캐스팅 필요함
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 가장 중요한 메소드
        LogManager.logPrint("position : " + position);
        View view = View.inflate(context, layout, null); // 뷰를 그려준다 layout이 가지고 있는거 써도된다. 여기선 View 사용
        MyItem item = data.get(position); // 데이터 생성
        ImageView img = (ImageView)view.findViewById(R.id.imageView); // View 그룹이 아니기 때문에 find 바로 못함. view.find ~
        TextView textView = (TextView)view.findViewById(R.id.textView);
        TextView textView2 = (TextView)view.findViewById(R.id.textView2);
        TextView textView3 = (TextView)view.findViewById(R.id.textView3);
        img.setImageResource(item.imgId); // 만든 뷰를 연결
        textView.setText(item.down);
        textView2.setText(item.title);
        textView3.setText(item.price + ""); // price가 int형이기 때문에 String으로 형변환

        return view;
    }
}
