package com.example.menuproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.ImageView;

/**
 * Created by skplanet on 2016-01-22.
 */
public class MyImageView extends ImageView{

    // 생성자 자동 생성 할것
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) { // 내부적으로 만들기 위해 오버라이딩
        menu.setHeaderTitle("이미지 편집");
        menu.add(0,101, 1, "이미지저장");
        menu.add(0,102, 1, "이미지저장");
        menu.add(0,103, 1, "이미지저장");
        super.onCreateContextMenu(menu);
    }
}
