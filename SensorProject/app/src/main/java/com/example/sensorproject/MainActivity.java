package com.example.sensorproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="Main";
    TextView tv1;

    int cnt1 = 0;

    SensorManager manager; // 센서 관리
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int accuracy= event.accuracy;
//            switch (accuracy){
//            }

            switch (event.sensor.getType()){
//                case Sensor.TYPE_LIGHT:
//                    cnt1++;
//                    float[] values = event.values;
//                    tv1.setText("cnt1 :"+cnt1 + " 값 :" +values[0]);
//                    break;
//                case Sensor.TYPE_TEMPERATURE:
//                    cnt1++;
//                    float[] values2 = event.values;
//                    tv1.setText("cnt2 :"+cnt1 + " 값 :" +values2[0]);
//                    break;
                case Sensor.TYPE_ROTATION_VECTOR: // 기울기 값에 따라 변경
                    float[] m = new float[9];
                    float[] values = new float[3]; // values 에 기울기 값이 들어간다
                    manager.getRotationMatrixFromVector(m, event.values);
                    manager.getOrientation(m, values);
                    cnt1++;
                    tv1.setText("cnt1 :"+cnt1 + " 값 :" +values[0]);
                    tv1.setText("방위각 :"+values[0] + " x축 :" +values[1]+ " y축 :" +values[2]);

                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_UI);
        manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_TEMPERATURE), SensorManager.SENSOR_DELAY_UI);
        manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        tv1 = (TextView)findViewById(R.id.textView2);

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL); // 디바이스 센서 다 가져오기

        for(Sensor s : list){
            Log.v(TAG, "" + s.getName() + ", ST : " + s.getPower() + ", 재조사명 : "+ s.getVendor());
        }


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
