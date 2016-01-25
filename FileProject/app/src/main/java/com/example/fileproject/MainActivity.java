package com.example.fileproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1("aaa.txt", "안녕하세요 ㅎㅎ");
                    doAction1("bbb.txt", "안녕하세요 ㅎㅎ");
                    doAction1("ccc.txt", "안녕하세요 ㅎㅎ");
                    doAction1("ddd.txt", "안녕하세요 ㅎㅎ");
                    doAction1("eee.txt", "안녕하세요 ㅎㅎ");
                    break;

                case R.id.button2:
                    doAction2("aaa.txt");
                    break;
                case R.id.button3:
                    doAction3();
                    break;
                case R.id.button4:
                    doAction4("aaa.txt");
                    break;
                case R.id.button5:
                    doAction5("aaa", "data", "sam.txt", "안녕하세요");
                    break;
                case R.id.button6:
                    doAction6();
                    break;
                case R.id.button7:
                    doAction7();
                    break;


            }
        }
    };


    // SharedPreferences 저장하기
    public void doAction6(){
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE); // context 가 가진 getSharedPreferences() 호출, MODE_PRIVATE은 0
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("id", "abc");
        editor.putInt("cnt", 3);
        editor.commit(); // 꼭 해야함. 안하면 메모리에만 저장
    }

    public void doAction7(){

    }




//    public void doAction1(String fName, String data){
//        PrintWriter os = null;
//        try {
//            os = new PrintWriter(openFileOutput(fName, MODE_PRIVATE));
////            os.write(data);
//            os.println();
//            Log.v(TAG, "filw write success");
//        }  catch (IOException e){
//            Log.v(TAG, "file Write error : " + e);
//        }finally {
//            if(os != null){
//                os.close();
//            }
//        }
//    };

    public void doAction1(String fName, String data){

        File file = getExternalFilesDir(null); // null로 하면 디폴트로 현재 프로젝트. 디렉토리 생성
        if(!file.exists()){
            file.mkdir();
        }
        Log.v(TAG, "filw write success" + file.getAbsolutePath());
        File f = new File(file, fName); // 파일생성
        PrintWriter os = null;
        try {
//            os = new PrintWriter(openFileOutput(fName, MODE_PRIVATE)); // 안드로이드
//            os.write(data);
            os = new PrintWriter(new FileWriter(f)); // 순수 자바, 퍼미션 필요
            os.println();
            Log.v(TAG, "filw write success");
        }  catch (IOException e){
            Log.v(TAG, "file Write error : " + e);
        }finally {
            if(os != null){
                os.close();
            }
        }
    };

    public void doAction2(String fName){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(openFileInput(fName)));
            String data = "";
            while((data = br.readLine())!= null){
                Log.v(TAG, "data : " + data);
            }
        } catch (IOException e) {
            Log.v(TAG, "read Data error : " + e);
        }finally {
            if(br != null){
                try {
                    br.close();
                }catch(IOException e){
                }
            }
        }
    }

    // 파일 목록 확인
    public void doAction3(){
        String[] fList = fileList();
        Log.v(TAG, "=================");
        for( String name : fList){
            Log.v(TAG, name);

        }
        Log.v(TAG, "=================");

    }

    public void doAction4(String fName){
        boolean flag = deleteFile(fName);
        Log.v(TAG, flag? "success" : "fail");

    }
    public  void doAction5(String dir1, String dir2, String fNmae, String data){
        String state = Environment.getExternalStorageState();
        if( !state.equals(Environment.MEDIA_MOUNTED )){
            Log.v(TAG, "외장메모리 인식불가");
            return;
        }

        File sdCard = Environment.getExternalStorageDirectory();

        File f = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM); // 사진 같은 공유 자원은 이곳에 넣는다.
        File[] fList = f.listFiles(); // DIRECTORY_DCIM 의 목록
        for(File file : fList){
            Log.v(TAG, file.getName());
        }



        Log.v(TAG, sdCard.getAbsolutePath()); // 외장메모리 경로


        // 외장에다 쓰기
        // 퍼미션 먼저 줄것
        File f1 = new File(sdCard, dir1);
        if(!f1.exists()){ //없으면
            f1.mkdir(); // 만든다.
        }

        File f2 = new File(f1, dir2); // f1 밑에 dir2 만든다.
        if(!f2.exists()){ //없으면
            f2.mkdir(); // 만든다.
        }

        File f3 = new File(f2, fNmae);
        PrintWriter pw = null;

        try{
            pw = new PrintWriter(new FileWriter(f3));
            pw.println(data);
            Log.v(TAG, "sdCard wrie success");
        }catch (IOException e){
            Log.v(TAG, "sdCard wrie error" + e);
        }finally {
            if(pw != null){
                pw.close();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);
        findViewById(R.id.button6).setOnClickListener(handler);
        findViewById(R.id.button7).setOnClickListener(handler);

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
