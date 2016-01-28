package com.example.locationproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        manager.removeUpdates(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 내가 만든 퍼미션 체크
//        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){ // 퍼미션 체크
//            // TODO 퍼미션 창 띄우기
//            return;
//        }

        // 자동 생성 퍼미션 체크
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: 퍼미션 창 띄우기
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener); // 마시멜로 버전은 무조건 실행되지 않고 버전 체크 해야한다.
    }

    int cnt = 0;
    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            cnt++;
            Location location1 = null;
            float length = location.distanceTo(location1); // 좌표 간 거리. 좌표간 거리를 저장할때 이상하면 저장 하지않게 하기 위해서  GPS 보정
            Log.v(TAG, String.format("위도 %f, 경도 %f, 고도 %f", location.getLatitude(), location.getLongitude(), location.getAltitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // enable 상태에서 일시적으로 안되는 경우, 엘리베이터
            switch (status){
                case LocationProvider.AVAILABLE: // 잘되는 상태
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    LocationManager manager;
    // 오버라이딩 전에 startActivityForResult를 선언해야한다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG, resultCode+"");

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

    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = (LocationManager)getSystemService(LOCATION_SERVICE); // 위치 서비스 얻기
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){ // 사용불가면 GPS 열기
            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 999);
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = manager.getLastKnownLocation("gps");
        if(location != null){
            Log.v(TAG, "마지막 위치정보");
            Log.v(TAG, String.format("위도 %f, 경도 %f, 고도 %f", location.getLatitude(), location.getLongitude(), location.getAltitude()));

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
