package com.example.locationproject;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    LocationManager manager;

    // 오버라이딩 전에 startActivityForResult를 선언해야한다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 999:
                switch (resultCode){
                    case RESULT_OK:
                        Toast.makeText(this, "설정완료", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(this, "GPS 사용불가", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = (LocationManager)getSystemService(LOCATION_SERVICE); // 위치 서비스 얻기
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            Log.v(TAG, "GPS 가용가능");
            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 999);
        }


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
