package com.example.locationproject;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
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

import com.google.android.gms.common.api.GoogleApiClient;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1();
                    break;
                case R.id.button2:
                    showMarkerPoint();
                    break;
            }
        }
    };

    GoogleApiClient apiClient;

    TMapView mMapView; // T맵
    public static String mApiKey = "d8a122f8-d03f-37ca-968a-aba76934c836";

    private void configureMapView() {
        mMapView.setSKPMapApiKey(mApiKey);
    }

    public void initView(){
        mMapView.setCenterPoint(126.977963, 37.56647);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mMapView.setIcon(bitmap); // 지도 중앙에 이미지 보이게 하기, 네비게이션에서 자동차 같은 개념
        mMapView.setIconVisibility(true); // 이미지 보일지 여부
        mMapView.setSightVisible(true);
        mMapView.setCompassMode(false);
    }

    public TMapPoint randomTMapPoint() {
        double latitude = ((double)Math.random() ) * (37.575113-37.483086) + 37.483086;
        double longitude = ((double)Math.random() ) * (127.027359-126.878357) + 126.878357;

        latitude = Math.min(37.575113, latitude);
        latitude = Math.max(37.483086, latitude);

        longitude = Math.min(127.027359, longitude);
        longitude = Math.max(126.878357, longitude);

        Log.v(TAG, latitude + " " + longitude);

        TMapPoint point = new TMapPoint(latitude, longitude);

        return point;
    }
    /**
     * showMarkerPoint
     * 지도에 마커를 표출한다.
     */
    public void showMarkerPoint() {

        Bitmap bitmap_i = BitmapFactory.decodeResource(getResources(), R.drawable.i_go); // > 아이콘


        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.i_location);

        TMapPoint point = new TMapPoint(37.566474, 126.985022);
        TMapMarkerItem item1 = new TMapMarkerItem();

        item1.setTMapPoint(point);
        item1.setName("SKT타워");
        item1.setVisible(item1.VISIBLE);
        item1.setIcon(bitmap);
        item1.setCalloutTitle("SKT타워");
        item1.setCalloutSubTitle("을지로입구역 500M");
        item1.setCanShowCallout(true);
        item1.setAutoCalloutVisible(true);
        item1.setCalloutRightButtonImage(bitmap_i);

        String strID = String.format("pmarker%d", 1);
        mMapView.addMarkerItem(strID, item1);  // 마커에 아이디를 다르게 해줘야한다. 문자열로



        point = new TMapPoint(37.55102510077652, 126.98789834976196);
        TMapMarkerItem item2 = new TMapMarkerItem();

        item2.setTMapPoint(point);
        item2.setName("N서울타워");
        item2.setVisible(item2.VISIBLE);
        item2.setCalloutTitle("청호타워 4층");
        item2.setCanShowCallout(true);
        item2.setCalloutRightButtonImage(bitmap_i);

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pin_tevent);
        item2.setIcon(bitmap);

        strID = String.format("pmarker%d", 2);

        mMapView.addMarkerItem(strID, item2);

    }




    public void doAction1(){
        Geocoder geocoder = new Geocoder(this, Locale.KOREAN);
        try {
            List<Address> list =  geocoder.getFromLocationName("서울시청", 5);
            if(list != null){
                for(Address address : list){
                    Log.v(TAG, "Address  :  위도 : " + address.getLatitude() + " 경도 : " + address.getLongitude() + " 기타 :" + address.getCountryName());
                }
            }
        } catch (IOException e) {
            Log.v(TAG, "getFromLocationName e : " + e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        manager.removeUpdates(listener);
    }

    PendingIntent pendingIntent;

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

            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener); // 마시멜로 버전은 무조건 실행되지 않고 버전 체크 해야한다.

        Intent intent = new Intent(this, MyReceiver.class); // 내가 만든 리시버를 동작하게 한다.
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT); // 방송 쓸 인텐트
        manager.addProximityAlert(37.56647, 126.977963, 100, -1, pendingIntent); // 반경 100에서 들어가거나 나오면 pendingIntent 동작
    }

    int cnt = 0;
    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mMapView.setCenterPoint(location.getLongitude(), location.getLongitude()); // 내 위치에 따라 지도 이동
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

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);

        mMapView = (TMapView)findViewById(R.id.view); // T맵 뷰 연결

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = (LocationManager)getSystemService(LOCATION_SERVICE); // 위치 서비스 얻기
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){ // 사용불가면 GPS 열기
            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 999);
        }

        configureMapView();
        initView();

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
