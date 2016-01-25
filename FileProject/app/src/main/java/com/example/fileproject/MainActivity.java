package com.example.fileproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
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
//                    doAction3("aaa.txt");
                    break;
                case R.id.button4:
//                    doAction4("aaa.txt");
                    break;

            }
        }
    };

    public void doAction1(String fName, String data){
         PrintWriter os = null;
        try {
            os = new PrintWriter(openFileOutput(fName, MODE_PRIVATE));
//            os.write(data);
            os.println();
            Log.v(TAG, "filw write success");
        }  catch (IOException e){
            e.printStackTrace();
        }finally {
            if(os != null){
                os.close();
            }
        }
    };

    public void doAction2(String fName){
        BufferedReader br = null;
        try {
            new InputStreamReader(openFileInput(fName));
            String data = "";
            while((data = br.readLine())!= null){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br != null){
                try {
                    br.close();
                }catch(IOException e){
                }
            }
        }
    }

    public void doAction3(){

    }

    public void doAction4(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);

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
