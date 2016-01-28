package com.example.jw.sensororientationproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {


	Context context = null;
	float azimuth;
	float pitch;
	float roll;
	final static int MAX = 30;
	Paint textPaint = null;
	Paint paint = null;
	Bitmap bitmap = null;
	int width, height, thick, length, w10, h10 = 0;
	
	
	void init(Context context){
		this.context = context;
		textPaint = new Paint();
		paint = new Paint();
		textPaint.setAntiAlias(true);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(40);
		paint.setAntiAlias(true);
		paint.setTextSize(60);

		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.compass);
	}
	
	public MyView(Context context) {
		super(context);
		init(context);
	}
	
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
		w10 = width / 10;
		h10 = height /10;
		thick = h10;
		length = w10 *  8;
		bitmap = Bitmap.createScaledBitmap(bitmap, w10 *7,w10 *7, false);
	}
	public void setNewData(float[] values){
		if(azimuth != values[0] || pitch != values[1] || roll != values[2]){
			azimuth = values[0] ;
			pitch = values[1] * 20;
			roll = values[2] * 20;
			invalidate();
		}		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		paint.setColor(Color.BLACK);
		Rect pitchRect = new Rect(w10, h10, w10+thick, h10 + length);
		canvas.drawRect(pitchRect, paint);
		Rect rollRect = new Rect(w10, h10*2 + length, w10 * 9, h10 *2 + length + thick);
		canvas.drawRect(rollRect, paint);

		
	
		float rollValue = roll <- MAX ?- MAX:roll > MAX ? MAX : roll;
		int rollCenter = rollRect.left + rollRect.width()/2;
		int rollLength = rollRect.width() - thick;
		int rollPos = rollCenter + (int)(rollLength /2 * rollValue /MAX);
		
		paint.setColor((int)rollValue == 0 ? Color.RED : Color.YELLOW);
		
		canvas.drawCircle(rollPos, rollRect.top + thick/2, 
				(int)(thick/2*0.9), paint);
		canvas.drawText("ROLL : " + (int)rollValue, rollRect.left, rollRect.top-5, textPaint);
		
		float pitchValue = pitch;
		if((Math.abs(pitch)) > 90){
			pitchValue = 180 - Math.abs(pitch);
			if(pitch < 0){
				pitchValue *= -1;
			}
		}
		
		pitchValue = pitchValue <- MAX ?- MAX:pitchValue > MAX ? MAX:pitchValue;
		int pitchCenter = pitchRect.top + pitchRect.height() / 2;
		int pitchLength = pitchRect.height() - thick;
		int pitchPos = pitchCenter + (int) (pitchLength / 2 * pitchValue / MAX);
		
		paint.setColor((int) pitchValue == 0 ? Color.RED : Color.YELLOW);
		canvas.drawCircle(pitchRect.left + thick/2, pitchPos, 
				(int)(thick/2 * 0.9), paint);
		canvas.drawText("PITCH : " + (int)pitchValue, pitchRect.left ,pitchRect.top-5, textPaint);
		
		Matrix matrix = new Matrix();
//		matrix.setScale(0.7f, 0.7f);
		matrix.postRotate(-azimuth, bitmap.getWidth() / 2, bitmap.getHeight()/2);
		matrix.postTranslate(rollCenter - bitmap.getWidth()/2 + thick, pitchCenter - bitmap.getHeight()/2);
		canvas.drawBitmap(bitmap, matrix, paint);
		canvas.drawText("AZIMUTH : " + (int)azimuth, rollCenter, 
				pitchCenter - bitmap.getHeight()/2 - 5, textPaint);
	}
	
}