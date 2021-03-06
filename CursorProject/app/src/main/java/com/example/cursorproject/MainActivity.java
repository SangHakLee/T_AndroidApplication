package com.example.cursorproject;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    ListView list;
    Cursor c;
//    SimpleCursorAdapter adapter; // 리시트뷰를 관리하는 어덥터
//    MyCursorAdapter adapter; // 이미지뷰를 위한 어덥터
    PersonAdapter adapter;

    ArrayList<Person> data = null;

    MyHelper helper;
    SQLiteDatabase db;

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doAction1();
        }
    };

    // 전화 번호 추가하기
    public void doAction1(){
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccounts(); // 계정 가져오기
        for(Account a : accounts){
            Log.v(TAG, a.name + "," + a.type); // 모든 계정 가져와서 로그 남기기
        }


        ContentValues values = new ContentValues();
        values.put(ContactsContract.RawContacts.ACCOUNT_NAME, "leesanghak7298@gmail.com"); // 위에서 가져온 accounts의 name
        values.put(ContactsContract.RawContacts.ACCOUNT_TYPE, "com.google"); // 위에서 가져온 accounts의 type
        Uri uri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawId = ContentUris.parseId(uri); // rawId 로 접근
        Log.v(TAG, "rawId : " + rawId);

        // 이름 추가
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "테스트유저");
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        // 번호 추가
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "123444556");
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        Log.v(TAG, "success");
    }

    // DB 열기
    public void doDBOpen(){
        if(helper == null){
            helper = new MyHelper(this, "myDB.db", null, 1);
        }
        db = helper.getWritableDatabase();
    }
    // DB 닫기
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

        findViewById(R.id.button).setOnClickListener(handler);

        helper = new MyHelper(this, "myDB.db", null, 1); //

        list = (ListView)findViewById(R.id.listView);



        db = helper.getReadableDatabase(); //읽기 전요
        c = db.query("person", null, null, null, null, null, null); // person 데이터 모두 불러오기

        //1.context 2.layout 3.cursor 4.가져오랴는 데이터 5.뷰 내부 데이터 6.데이터 변경시 알려주는 플래그
        // 4, 5버은 커스텀 뷰를 만들지 않았기 때문에 android 기본 사용

//        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, new String []{"name", "age"}, new int[]{android.R.id.text1,android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER); // 기본 뷰
//        adapter = new SimpleCursorAdapter(this, R.layout.item, c, new String []{"name", "type"}, new int[]{R.id.textView2,R.id.textView3}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER); // 커스텀 뷰
//        adapter = new MyCursorAdapter(this, R.layout.item, c, true);


        data = new ArrayList<Person>();
        Person person;
        while (c.moveToNext()){ // DB 검색한다.
            person = new Person();
            // DB의 값을 가져온다.
            person._id = c.getInt(0);
            person.name = c.getString(1);
            person.age = c.getInt(2);
            person.type = c.getInt(3);
            data.add(person);  // ArrayList에 넣는다.
        }

        // 더 이상 DB 접근 안하기 때문에 모두 닫는다.
        c.close();
        db.close();
        helper.close();

        adapter = new PersonAdapter(this, R.layout.item, data); // 미리 ArrayList형 Person 객체 data를 만들어야한다.



        list.setAdapter(adapter); // 만든 어덥터랑 연결

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
