package com.example.graphicproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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
        if(mCanvas == null){ // 멤버 canvas가 널이면 넣어줌
            mCanvas = canvas;
            width = getWidth(); // 뷰의 넓이 리턴, onDraw가 호출이 되야 getWidth() 할 수 있다. init()에서 불가
            height = getHeight(); // 뷰의 높이 리턴
            left = (width-100) / 2; // 맨처음 사각형을 중앙으로 오기 위해서
            top = (height-100) / 2;
        }
//        canvas.drawRect(100, 100, 200, 200, paint);
        canvas.drawRect(left, top, left+100, top+100, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            // 스위치 케이스문이나 if문 없으면 모든 액션에 작업을 한다.
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(context, String.format("x : %f, y : %f", event.getX(), event.getY()), Toast.LENGTH_SHORT).show();
                doAction(event.getX(), event.getY());
                return  true; // 여기서는 break 말고 return;
//                break;
        };
        return super.onTouchEvent(event);
    }

    Canvas mCanvas = null; //밖에서 접근하기 위함

    int left, top; // 사각형
    int width, height;
    void doAction(float x, float y){ // 클릭시 사각형 그리기
        left = (int) x;
        top = (int) y;
        invalidate(); // 다시 그려주는거 onDraw의 canvas가 호출된다.
    }
}
