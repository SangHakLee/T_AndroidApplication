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

    // 폰에 설치되고 최초 한번만. 지웠다 깔아야함
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE person ( _id INTEGER AUTO_INCREMENT PRIMARY KEY, name TEXT, age INTEGER, type INTEGER);";


        Log.v(TAG, "onCreate");
        // execSQL 는 반드시 try catch
        try{
            db.execSQL(sql); // CREATE
            db.execSQL("INSERT INTO person(name, age, type) VALUES('kim', 33, 1);");
            db.execSQL("INSERT INTO person(name, age, type) VALUES('lee', 13, 0);");
            db.execSQL("INSERT INTO person(name, age, type) VALUES('jo', 35, 2);");
            db.execSQL("INSERT INTO person(name, age, type) VALUES('oh', 10, 1);");
            db.execSQL("INSERT INTO person(name, age, type) VALUES('wow', 24, 2);");
            db.execSQL("INSERT INTO person(name, age, type) VALUES('nono', 55, 0);");
            Log.v(TAG, "db success");
        }catch (android.database.SQLException e){
            Log.v(TAG, "db error :" + e);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, String .format("old : %d, new : %d", oldVersion, newVersion));

    }

    // 많이 써기 때문에 추가 재정의
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.v(TAG, "onOpen");

    }
}
