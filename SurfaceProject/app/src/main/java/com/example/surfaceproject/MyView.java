package com.example.surfaceproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by skplanet on 2016-01-25.
 * SurfaceView 만들기
 */
public class MyView extends SurfaceView{
    // 생성자 3개 필요

    Context context;

    public void init(Context context){
        this.context = context;
    }

    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
