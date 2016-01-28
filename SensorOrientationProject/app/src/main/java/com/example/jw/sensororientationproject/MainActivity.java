package com.example.jw.sensororientationproject;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SensorManager manager = null;
    Sensor roationSensor;
    MyView myView = null;
    float[] orientation  = new float[3];
    float[] rotationMatrix = new float[9];
    float[] mGravity = null;
    float[] mGeoMagnetic = null;

    SensorEventListener sListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ROTATION_VECTOR :
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
                    SensorManager.getOrientation(rotationMatrix, orientation);
                    orientation[0]= (int) ( Math.toDegrees( orientation [0] ) + 360 ) % 360;
                    Log.v(TAG, String.format("azimuth : %f, pitch : %f , roll : %f", orientation[0],orientation[1],orientation[2]));
                    myView.setNewData(orientation);
                    break;
//                case Sensor.TYPE_ACCELEROMETER:
//                    mGravity = event.values.clone();
//                    break;
//                case Sensor.TYPE_MAGNETIC_FIELD:
//                    mGeoMagnetic = event.values.clone();
//                    break;
//                f (mGravity != null && mGeoMagnetic != null) {
//                    float[] R = new float[16];
//                    SensorManager.getRotationMatrix(R, null, mGravity, mGeoMagnetic);
//                    SensorManager.getOrientation(R, values);
//
//                    mTxtOrient.setText("방향 = " + mOrientCount++ + "회" +
//                            "\nazimuth:" + Radian2Degree(values[0]) +
//                            "\npitch:" + Radian2Degree(values[1]) +
//                            "\nroll:" + Radian2Degree(values[2]));
//                }
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };
    @Override
    protected void onPause() {
        manager.unregisterListener(sListener);
        super.onPause();
    }



    @Override
    protected void onResume() {
        manager.registerListener(sListener, roationSensor, SensorManager.SENSOR_DELAY_UI);
        manager.registerListener(sListener, manager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
        manager.registerListener(sListener, manager.getDefaultSensor(
                Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI);
        super.onResume();
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
        myView = (MyView)findViewById(R.id.view);


        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        roationSensor = manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
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
