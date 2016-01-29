package com.example.facebookproject;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

/**
 * Created by skplanet on 2016-01-29.
 */
public class MyApplication extends Application {

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        FacebookSdk.sdkInitialize(this);
    }
}
