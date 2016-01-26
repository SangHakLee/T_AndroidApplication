package com.example.cursorproject;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by skplanet on 2016-01-26.
 */
public class MyCursorAdapter extends CursorAdapter {
    // 메소드 newView bindView 오버라이딩
    // 생성자 생성

    int layout; // 여기서 layout은 Main에서 넘기 R.id.item 레이아웃

    int[] imgRes = {R.drawable.icon01, R.drawable.icon02, R.drawable.icon03};

    public MyCursorAdapter(Context context, int layout,  Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // 1. context 2.xml 3.view구룹
        View view = View.inflate(context, layout, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // newView에서 생선된 view가 넘어온다.

    }
}
