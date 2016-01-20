package com.example.recyclerproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by skplanet on 2016-01-20.
 * 어답터 생성
 * ViewHolder 를 무조건 만들어야한다.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{ //2. extend해준다. 내가 만든 Adapter 로

    // 3. 멤버 변수 선언
    Context context;
    int initLayout;
    ArrayList<MyItem> data;

    // 4, 생성자
    public MyAdapter(Context context, int initLayout, ArrayList<MyItem> data) {
        this.context = context;
        this.initLayout = initLayout;
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder{ // 1. 이 클래스를 먼저 만드나.

        // 뷰에 쓰일 변수 선언
        ImageView img;
        TextView textView;
        TextView textView2;
        TextView textView3;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.imageView);
            textView = (TextView)itemView.findViewById(R.id.textView);
            textView2 = (TextView)itemView.findViewById(R.id.textView2);
            textView2 = (TextView)itemView.findViewById(R.id.textView2);
        }
    }



    // 2번 하고 나서 alt + i로 자동완성해준다.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 5. 뷰를 인플래트 한다.
        View view = View.inflate(context, initLayout, null); // 여기서는 재활용을 위한 처리 필요없음 자동으로 재황용
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 6. 데이터 넣어준다.
        MyItem item = data.get(position);
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
