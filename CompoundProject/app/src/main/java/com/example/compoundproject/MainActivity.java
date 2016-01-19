package com.example.compoundproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ImageTextView.OnImageClickListener handler = new ImageTextView.OnImageClickListener(){

        @Override
        public void onImageClick(View v, ImageTextData data) {
            switch (v.getId()){
                case R.id.view :
                    Log.v(TAG, "view : " + data.text);
                    break;

                case R.id.view2 :
                case R.id.view3 :
                    Toast.makeText(MainActivity.this, data.text, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageTextView imageTextView = (ImageTextView)findViewById(R.id.view);
        ImageTextData data = new ImageTextData(R.drawable.down, "data");
        imageTextView.setData(data);
        imageTextView.setOnImageListener(handler);

        ImageTextView imageTextView2 = (ImageTextView)findViewById(R.id.view2);
        ImageTextData data2 = new ImageTextData(R.drawable.down2, "data2");
        imageTextView.setData(data2);
        imageTextView.setOnImageListener(handler);

        // attrs.xml에서 연동
        ImageTextView imageTextView3 = (ImageTextView)findViewById(R.id.view3);
        imageTextView.setOnImageListener(handler);

    }
}
