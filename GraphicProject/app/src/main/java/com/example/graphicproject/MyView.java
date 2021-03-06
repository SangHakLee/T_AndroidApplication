package com.example.graphicproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
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
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        this.context = context;

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.down);
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
//        canvas.drawRect(left, top, left+100, top+100, paint);

        canvas.drawColor(Color.WHITE); // 잔상제거
//        doDrawRect(canvas); // 기본방법

//        doDrawRect(); // 우리 방법
//        doDrawLine();
//        doDrawPath();
//        doDrawBitmap();
        doDrawDashEffect();
        super.onDraw(canvas);
    }

    // 기본 방법
//    void doDrawRect(Canvas canvas){
//        canvas.drawRect(left, top, left + 100, top + 100, paint);
//    }

    void doDrawRect(){
        mCanvas.drawRect(left, top, left+100, top+100, paint);
    }

    void doDrawLine(){
        mCanvas.save();
        mCanvas.drawLine(100, 100, 200, 200, paint);
        mCanvas.translate(100, 0); // 좌표 이동
        mCanvas.drawLine(100, 100, 200, 200, paint);
        mCanvas.restore(); //translate() 한거 복구. 좌표 0,0 로 이동
    }

    void doDrawPath(){
        paint.setTextSize(40);
        float charSize = paint.measureText("가");
        charSize = paint.measureText("A");
        charSize = paint.measureText("1");

        mCanvas.save();
        Path path = new Path(); // Path는 계속 같은 모양을 만들 때 주로 쓰임
        paint.setStyle(Paint.Style.FILL);
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(250, 150);
        path.lineTo(200, 200);
        path.lineTo(200, 200);
        path.lineTo(100, 200);
        path.lineTo(150, 150);
        path.lineTo(100, 100);
        mCanvas.drawPath(path, paint);

        mCanvas.translate(0, 300); // 위치 바꿔서
        mCanvas.drawPath(path, paint); // 다시 그리기

        mCanvas.translate(0, 300); // 위치 바꿔서
        mCanvas.drawPath(path, paint); // 다시 그리기

        mCanvas.restore();
    }

    void doDrawTextPath(){
        // 책보고 할것
        Path path = new Path();
        path.moveTo(100, 100);
        path.addCircle(200, 200, 100, Path.Direction.CW);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            // 스위치 케이스문이나 if문 없으면 모든 액션에 작업을 한다.
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(context, String.format("x : %f, y : %f", event.getX(), event.getY()), Toast.LENGTH_SHORT).show();
//                doAction(event.getX(), event.getY());
                return  true; // 여기서는 break 말고 return; 이벤트 처리가 하위뷰에 전달 할건지 말건지. 전달하면false 안하면 true
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


    Bitmap bitmap; // 이미지 관리

    void doDrawBitmap(){
        Matrix matrix = new Matrix();

        mCanvas.drawBitmap(bitmap, null, new Rect(100, 100, 200, 200), paint);
        mCanvas.drawBitmap(bitmap, new Rect(50, 50, 70, 70), new Rect(100, 100, 200, 200), paint); // 앞에 만큼크기에 뒤에 사이즈를 그림
    }

    void doDrawDashEffect(){
        paint.setStrokeWidth(5);
        float[] intervals = {10,10,10};
        DashPathEffect effect = new DashPathEffect(intervals, 3);
        paint.setPathEffect(effect);
        mCanvas.drawRect(100, 100, 200, 200, paint);
    }
}
