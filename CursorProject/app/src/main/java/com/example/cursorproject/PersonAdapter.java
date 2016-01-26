package com.example.cursorproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by skplanet on 2016-01-26.
 */
public class PersonAdapter extends BaseAdapter{

    Context context;
    int layout;

    ArrayList<Person> list;

    int[] imgRes = {R.drawable.icon01, R.drawable.icon02, R.drawable.icon03};


    public PersonAdapter(Context context, int layout, ArrayList<Person> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO viewHolder 만들기

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null){
            convertView = View.inflate(context, layout, null);
        }
        Person person = list.get(position);
        TextView tv = (TextView)convertView.findViewById(R.id.textView2);
        tv.setText(person.name + person.age); // cursor에서 가져와서 텍스트 뷰에 뿌린다. 이름과 나이
        ImageView img = (ImageView)convertView.findViewById(R.id.imageView);
        img.setImageResource(imgRes[person.type]);
        return convertView;
    }
}
