package org.tacademy.FragmentProject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText et;
    FristFragment fristFragment;
    SecondFragment secondFragment;
    FragmentTransaction ft;
    Fragment fragment;
    FragmentManager manager;
    public void dochangeData(String data){
        et.setText(data);
    }
    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button2 :
                    doAction1();
                    break;
                case R.id.button4 :
                    doAction2();
                    break;
            }
        }
    };
    void doAction2(){
        fragment = manager.findFragmentByTag("second");
        if(fragment == null) {
            secondFragment = new SecondFragment();
            ft = manager.beginTransaction();
            ft.replace(R.id.main, secondFragment, "second");
            ft.commit();
        }
    }
    int cnt = 0;
    void doAction1(){
        fragment = manager.findFragmentByTag("first");
        if(fragment == null) {
            Log.v(TAG, "first");
            cnt++;
            Bundle bundle = new Bundle();
            bundle.putInt("cnt", cnt);
            bundle.putString("name", "android");
            fristFragment = new FristFragment();
            fristFragment.setArguments(bundle);
            ft = manager.beginTransaction();
            ft.replace(R.id.main, fristFragment, "first");
            ft.commit();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        et = (EditText)findViewById(R.id.editText3);

        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        manager = getSupportFragmentManager();
        if(savedInstanceState == null) {
            cnt++;
            Bundle bundle = new Bundle();
            bundle.putInt("cnt", cnt);
            bundle.putString("name", "android");
            fristFragment = new FristFragment();

            fristFragment.setArguments(bundle);

            if(fragment instanceof  FristFragment) {
                ((FristFragment) fragment).setData("test", cnt);
            }


            fristFragment.setArguments(bundle);
            fristFragment.setOnMyListener(new FristFragment.MyListener() {
                @Override
                public void receiveMessage(String data) {
                    dochangeData(data);
                }
            });
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.main, fristFragment);
            ft.commit();
        }
//        fristFragment = (FristFragment)manager.findFragmentById(R.id.fragment);
    }
}
