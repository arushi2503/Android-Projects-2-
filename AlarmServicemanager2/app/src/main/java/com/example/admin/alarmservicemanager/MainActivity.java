package com.example.admin.alarmservicemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Alarm Variables
    private AlarmManager alarmMgr,alarmManager;
    private PendingIntent alarmIntent,alarmIntentOne;
    private Button elapsedWakeUp, rtcWakeup, cancelAlarm;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elapsedWakeUp = (Button)findViewById(R.id.button);
        rtcWakeup = (Button)findViewById(R.id.button2);
        cancelAlarm = (Button)findViewById(R.id.button3);
        BroadcastReceiver br = new SampleBootReceiver();
        alarmMgr = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
        alarmManager = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(br, filter);


        elapsedWakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName receiver = new ComponentName(MainActivity.this, SampleBootReceiver.class);
                PackageManager pm = MainActivity.this.getPackageManager();
                Toast.makeText(getApplicationContext(),"ELAPSED WAKE UP ALARM SET!!!",Toast.LENGTH_LONG).show();
                pm.setComponentEnabledSetting(receiver,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
                Intent intent = new Intent(MainActivity.this, SampleBootReceiver.class);
                intent.putExtra("alarmType","ELAPSED");
                alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + 2*1000*60,
                        AlarmManager.INTERVAL_DAY, alarmIntentOne);
                alarmIntentOne = PendingIntent.getBroadcast(MainActivity.this, 123456789, intent,0);

                    //mp=MediaPlayer.create(MainActivity.this, R.raw.alarm   );
                //   mp.start();

            }
        });

        rtcWakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the alarm to start at 8:30 a.m.
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 2);
                calendar.set(Calendar.MINUTE, 18);
                Intent intent = new Intent(MainActivity.this, SampleBootReceiver.class);
                Toast.makeText(getApplicationContext(),"RTC WAKE UP ALARM SET!!!",Toast.LENGTH_LONG).show();
                intent.putExtra("alarmType","RTC");
                // setRepeating() lets you specify a precise custom interval--in this case,
                // 20 minutes.
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        5*60*1000 , alarmIntent);
                alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
            }
        });


        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmMgr = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                // If the alarm has been set, cancel it.
                if (alarmMgr != null) {
                    Toast.makeText(getApplicationContext(),"ALARM OFF!!!",Toast.LENGTH_LONG).show();
                    ComponentName receiver = new ComponentName(MainActivity.this, SampleBootReceiver.class);
                    PackageManager pm = MainActivity.this.getPackageManager();

                    pm.setComponentEnabledSetting(receiver,
                            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                            PackageManager.DONT_KILL_APP);

                    alarmMgr.cancel(alarmIntentOne);
//                    alarmManager.cancel(alarmIntent);
                }

            }
        });


    }

}
