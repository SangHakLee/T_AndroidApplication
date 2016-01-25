package com.example.sqliteproject.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by skplanet on 2016-01-25.
 */
public class MyHelper extends SQLiteOpenHelper{
    // 2가지 오버라이딩 ctrl + i
    // 생성자 1개 생성

    private static final String TAG = "Main";

    Context context; // 나중에 쓸 수도 있으니 context 멤버로

    // context, DB명, factory는 null, 버전정보
    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, String .format("old : %d, new : %d", oldVersion, newVersion));

    }
}
