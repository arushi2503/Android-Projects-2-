package com.example.admin.multithreading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.admin.multithreading.AndroidApplication.SecondThread;

public class MultiThreadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_threading);
    }

    public void pauseMainThread(View v) {
        try {
            Thread.sleep(6 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pauseSecondThread(View v) {
        AndroidApplication app = new AndroidApplication();
        SecondThread thread = app.new SecondThread();
        thread.start();

    }
}
