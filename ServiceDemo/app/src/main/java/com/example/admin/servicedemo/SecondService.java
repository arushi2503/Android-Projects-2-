package com.example.admin.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SecondService extends IntentService{

    public SecondService() {
        super("My Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Service is Created",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service is Started",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("Service test","From the onHandleIntent method");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service is Stopped",Toast.LENGTH_LONG).show();
    }
}
