package com.example.graphicproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by skplanet on 2016-01-25.
 */
public class MyView extends View {
    // 생성자 위에 3개 생성
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

    Context context;
    private void init(Context context){
        paint = new Paint();
        paint.setColor(Color.RED);
        this.context = context;
    }

    Paint paint= null; // 그리는 붓 같은 개념

    // 실제 그리는 메소드
    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect();
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
