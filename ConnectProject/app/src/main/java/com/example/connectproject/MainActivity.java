package com.example.connectproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    ConnectivityManager manager; // 네크퉈크 연결 가능한지 알려주는 매니저

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    doAction1();
                    break;
            }
        }
    };

    // 내 폰 아이피 얻기
    public final static int INET4ADDRESS = 1;
    public final static int INET6ADDRESS = 2;
    public static String getLocalIpAddress(int type) {
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) { // NetworkInterface 네트워크 정보
                NetworkInterface intf = ( NetworkInterface ) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) { // 현재 핸드폰 IP getInetAddresses
                    InetAddress inetAddress = ( InetAddress ) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        switch (type) {
                            case INET6ADDRESS:
                                if (inetAddress instanceof Inet6Address) {
                                    return inetAddress.getHostAddress().toString();
                                }
                                break;
                            case INET4ADDRESS:
                                if (inetAddress instanceof Inet4Address) {
                                    return inetAddress.getHostAddress().toString();
                                }
                                break;
                        }

                    }
                }
            }
        } catch (SocketException ex) {
        }
        return null;
    }

    WifiManager wifiManager;
    // registerReceiver 에서 등록한 것
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){ // 와이파이 잡았다는 뜻
                List<ScanResult> list = wifiManager.getScanResults();
                for(ScanResult s : list){ // 현재 잡을 수 있는 모든 와이파이 잡는다
                    Log.v(TAG, String.format("이름 : %s, 세기 : %d", s.SSID, s.level));
                }
            }
        }
    };
    // 네트워크 상태 가져오기
    public void doAction1(){

        // 주위에 있는 wifi 정보 가져오는 것
        IntentFilter filter = new IntentFilter(); // 인텐트를 잡는다
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION); // 와이파이가 검색됬다는 뜻
        registerReceiver(receiver, filter); // 와이파이가 검색되면 여기로 날림
        wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
        wifiManager.startScan(); // 스캔한다.  퍼미션 추가해야함


//        // 와이파이에 잡힌 아이피 가져오기
//        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//        DhcpInfo dhcpInfo = wm.getDhcpInfo() ;
//        WifiInfo wifiInfo = wm.getConnectionInfo();
//        int serverIp = dhcpInfo.gateway;
//        int myIp = wifiInfo.getIpAddress();
//        String myIpAddress = String.format(
//                "%d.%d.%d.%d",
//                (myIp & 0xff),
//                (myIp >> 8 & 0xff),
//                (myIp >> 16 & 0xff),
//                (myIp >> 24 & 0xff));
//        String ipAddress = String.format(
//                "%d.%d.%d.%d",
//                (serverIp & 0xff),
//                (serverIp >> 8 & 0xff),
//                (serverIp >> 16 & 0xff),
//                (serverIp >> 24 & 0xff));
//        Log.v(TAG, "ipAddress : " + ipAddress);
//        Log.v(TAG, "myIpAddress : " + myIpAddress);
//
//        NetworkInfo info = manager.getActiveNetworkInfo();
//        if( info == null){ // 네트워크 없음
//            Log.v(TAG, "현재 사용할 수 있는 네트워크 없음");
//        }else{
//            if(ConnectivityManager.TYPE_MOBILE == info.getType()){
//                Log.v(TAG, "3G 상태");
//            }else if(ConnectivityManager.TYPE_WIFI == info.getType()){
//                Log.v(TAG, "WIFI 상태");
//                info.getState();
//            }
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(handler);

        manager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE); // 내 폰에 있는 시스템 기능 가져오기

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
