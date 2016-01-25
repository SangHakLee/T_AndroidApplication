package com.example.graphicproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by skplanet on 2016-01-25.
 */
public class MyView extends View {
    // 생성자 위에 3개 생성
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
