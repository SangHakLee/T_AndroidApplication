package com.example.compoundproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by skplanet on 2016-01-19.
 * 커스템 레이아웃 만들기
 *
 * 1. 뷰를 상속해서 만들고
 * 2, 생성자 만들고
 * 3. context 생성자 만들고
 * 3-2. 멤버에 Context context 만들고
 * 4. 모든 생성자에서 init(context);
 */
public class ImageTextView extends FrameLayout {
    // 여기서 Alt + insert 하면 자동완성 가능하다

    Context context; //
    ImageView img;
    TextView tv;
    ImageTextData data;
    OnImageClickListener listener;
    public void setOnImageListener(OnImageClickListener listener){ // 커스텀 이벤트 리스너
        this.listener = listener;
    }
    public interface OnImageClickListener{
        void onImageClick(View v, ImageTextData data);
    }

    public String getDataText(){ // data getter
        return data.text;
    }

    public void setData(ImageTextData data){ // ImageTextData 클래스에서 가져온 이미지, 데이터 set 하는 함수
        this.data = data;
        img.setImageResource(data.img);
        tv.setText(data.text);
    }

    private void init(Context context){ // 화면 배치
        this.context = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 아까 만든 xml 불러오기. 바로 못 불러온다 context가 가지고 있는거기 때문에. context.로 접근
        inflater.inflate(R.layout.item, this);
        img = (ImageView)findViewById(R.id.imageView);
        tv = (TextView)findViewById(R.id.textView);
        setOnClickListener(new View.OnClickListener(){// 만든 커스텀 이벤트 리스너를 부모 객체에 연결
            @Override
            public void onClick(View v) {
                // 커스텀 이벤트 리스너 연결
                if(listener != null){
                    listener.onImageClick(ImageTextView.this, data); // 그냥 this 하면 init이기 때문에 명시적으로
                }else{
                    Log.v("ERROR", "listener is null");
                }
            }

        });
    }


    public ImageTextView(Context context) {
        super(context);
        init(context);
    }

    public ImageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImageTextView);
        if(data == null){
            data = new ImageTextData();
            data.text = ta.getString(R.styleable.ImageTextView_title1);
            data.img = ta.getResourceId(R.styleable.ImageTextView_imgSrc, R.mipmap.ic_launcher);
        }
        setData(data);
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
