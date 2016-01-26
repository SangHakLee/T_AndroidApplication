package com.example.sqliteproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.sqliteproject.helper.MyHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    SQLiteDatabase db;
    MyHelper helper;


    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1("Kim", 40, 1);
                    break;
                case R.id.button2:
                    doAction2("Lee", 20, 20, 2);
                    break;
                case R.id.button3:
                    doAction3();
                    break;
                case R.id.button4:
                    // SELECT name, age, type
                    // FROM person
                    // WHERE LIKE '%L%'
                    // ORDER BY age DESC
                    // LIMIT 2, 3
                    doAction4("person", new String[]{"name", "age", "type"}, "K", "age DESC", "2,3"); // 불러오려는 컬럼은 배열로 1.테이블명 2. 가져올 칼럼 3.조건식대입문(like) 4.order 5.limit
                    break;
                case R.id.button5:
                    doAction5(1);
                    break;
            }
        }
    };

    // DB 생성
    // DB 추가 ContentValues
    public void doAction1(String name, int age, int type){
        doDBOpen();

        // CRUD 작업
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("type", type);

        try{
            long id = db.insert("person", null, values); // 1. 테이블명, 2.?? 3, ContentValues(들어 가는 값)
            Log.v(TAG, id > 0 ? "insert success" : "insert fail"); // db.insert하고 나온 반환값이 1이상이어야한다.
        }catch(SQLException e){
            Log.v(TAG, "insert error : " + e);
        }
//        String sqlFormat = "insert into person(name, age, type) values('%s', %d, %d);";
//        String sql = String.format(sqlFormat, name, age, type);
//        Log.v(TAG, "sql : " + sql);
//        try{
//            db.execSQL(sql);
//            Log.v(TAG, "insert success");
//        }catch(SQLException e){
//            Log.v(TAG, "insert error : " + e);
//        }

        doDBClose();
    }

    // DB 갱신
    public void doAction2(String newName, int newAge, int wid, int wtype){
        doDBOpen();
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("age", newAge);

        // update person set nam e = 'ddd', age= 44 where _id > 2 and type = 0
        String wStr = "_id > ? and type = ?";
        String [] wContent = {wid + "", wtype+""};
        try{
            int count = db.update("person", values, wStr, wContent);
        }catch (SQLException e){
            Log.v(TAG, "SQL error = "+ e);
        }finally {

        }
        doDBClose();
    }

    // SELECT 문
    public void doAction3(){
        doDBOpen();
        try{
            // select * from person;
//            db.query("person", null, null, null, null, null); // 1.DB명 , , , 5.그룹By, 6.Having
            Cursor c = db.query("person", null, null, null, null, null, null); // 반환 값이 Cursor
            while (c.moveToNext()){ // 다음 것이 있으면 실행
                int idx = c.getColumnIndex("_id"); // _id 컬럼의 인덱스 값을 가져온다.
                int id = c.getInt(idx); // 현재 위치의 _id 칼럼의 값
                String name = c.getString(c.getColumnIndex("name"));
                int age = c.getInt(2); // 우리가 알고 있는 INSERT문에 들어갔던 그 순서 인덱스 번호
                int type = c.getInt(3);
                Log.v(TAG, String.format("%d %s %d %d", id , name, age, type));
            }

            c.close(); // 다 하고 나서 close 꼭 할것
        }catch (SQLException e){
            Log.v(TAG, "SQL error = "+ e);
        }

        doDBClose();
    }

    // 조건식에 맞는 SELECT
    public void doAction4(String tableName, String[] colNames, String wStr, String order, String limit){ // wStr이 조건절 여기서는 LIKE 문
        doDBOpen();
        try{
            String[] str = {"%"+wStr+"%"}; // db.query의 4번째 조건식 ?에 들어갈 내용

            // 1.테이블명 2. 가져올 칼럼 3. 조건식 4.조건식에 들어갈 내용 5.gruop 6.having 7.order 8.limit
            Cursor c = db.query(tableName, colNames, "name LIKE ?", str, null, null, order, limit); // 반환 값이 Cursor
            while (c.moveToNext()){ // 다음 것이 있으면 실행
                String name = c.getString(0); // 이번엔 name이 0번째
                int age = c.getInt(1);
                int type = c.getInt(2);
                Log.v(TAG, String.format(" %s %d %d", name, age, type));
            }

            c.close(); // 다 하고 나서 close 꼭 할것
        }catch (SQLException e){
            Log.v(TAG, "SQL error = "+ e);
        }
        doDBClose();
    }

    // 삭제
   public void doAction5(int type){
       doDBOpen();
       String wStr = "type = ?";
       String [] wContent = {type + ""};
       try{
           int count = db.delete("person", wStr, wContent); // 삭제된 레코드의 개수가 반환
           Log.v(TAG, "SQL 삭제된 레코드 개수 = "+ count);

       }catch (SQLException e){
           Log.v(TAG, "SQL error = "+ e);
       }
       doDBClose();
    }

    // DB 열기
    public void doDBOpen(){
        if(helper == null){
            helper = new MyHelper(this, "myDB.db", null, 1);
        }
        db = helper.getWritableDatabase();
    }

    public void doDBClose(){
        if(db != null){
            if(db.isOpen()){
                db.close();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);
        helper = new MyHelper(this, "myDB.db", null, 1); // myDB.db 이름의 버전이 1
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
