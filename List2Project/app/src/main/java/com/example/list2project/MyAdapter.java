package com.example.list2project;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public void addData(MyItem item){
        data.add(item); // 이렇게 하면 UI가 변경되지 않는다.
//        notifyDataSetChanged();
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

    class ViewHolder{ // 뷰를 그릴때 한번만 찾기 위한\ㅁ
        ImageView img;
        TextView textView;
        TextView textView2;
        TextView textView3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // 가장 중요한 메소드
        ViewHolder holder = null; // 한번 찾아서 넣어줄 객체

        if( convertView == null ){ // 없을때만 생성. 재사용하기 위해
            convertView = View.inflate(context, layout, null); // 뷰를 그려준다 layout이 가지고 있는거 써도된다. 여기선 View 사용. 이 과정이 오래 걸리기 때문에 재사용하는게 좋다.
            LogManager.logPrint("position : " + position);
            holder = new ViewHolder();
            holder.img =  (ImageView)convertView.findViewById(R.id.imageView);
            holder.textView = (TextView)convertView.findViewById(R.id.textView);
            holder.textView2 = (TextView)convertView.findViewById(R.id.textView2);
            holder.textView3 = (TextView)convertView.findViewById(R.id.textView3);

            convertView.setTag(holder); // 만들어진 홀더를 convertView 에 넣어준다. convertView가 있으면 재사용하기 위해서
        }else{
            holder = (ViewHolder)convertView.getTag(); // 이미 만들어진 holder Object를 재사용한다.
        }
        final MyItem item = data.get(position); // 데이터 생성
//        ImageView img = (ImageView)convertView.findViewById(R.id.imageView); // View 그룹이 아니기 때문에 find 바로 못함. convertView.findViewById
//        TextView textView = (TextView)convertView.findViewById(R.id.textView);
//        TextView textView2 = (TextView)convertView.findViewById(R.id.textView2);
//        TextView textView3 = (TextView)convertView.findViewById(R.id.textView3);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show(); // item.title 하면 에러.이너 클래스 안에 있기 때문에. 이너 클래스가 더 먼저 메모리 할당된다. 그래서 item은 final 변수가 되어야한다. final 변수는 메모리에 먼저 올라가기 때문에
            }
        });
        holder.img.setImageResource(item.imgId); // 만든 뷰를 연결
        holder.textView.setText(item.down);
        if(position % 3 == 0){ // 재사용하기 때문에 원하지 않은 조건에 들어갈 수도 있다.
            holder.textView2.setTextColor(Color.RED);
        }else{
            // 반드시 else까지 넣어준다. 플리킹과 재활용 때문에 이상한 뷰가 생길수도 있다.
            holder.textView2.setTextColor(Color.BLACK);
        }
        holder.textView2.setText(item.title);
        holder.textView3.setText(item.price + ""); // price가 int형이기 때문에 String으로 형변환

//        return view;
        return convertView; // 이젠 convertView를 리턴한다. 재활용. convertView가 있으면 다시 만들지 않고 그냥 사용
    }
}


