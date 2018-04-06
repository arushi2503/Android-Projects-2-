package com.example.admin.admobdemo;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

/**
 * Created by Admin on 04/04/2018.
 */

public class myApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //initalize the admob app
        MobileAds.initialize(this, getString(R.string.admob_app_id));
    }
}
