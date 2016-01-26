package com.example.cursorproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by skplanet on 2016-01-26.
 */
public class PersonAdapter extends BaseAdapter{

    Context context;
    int layout;

    ArrayList<Person> list;

    public PersonAdapter(Context context, int layout, ArrayList<Person> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
