package com.example.admin.alarmservicemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class SampleBootReceiver extends BroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
      //  if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
            Log.i("Inside receiver","lalalala");
            /*intent.getStringExtra("alarmType");

            if(intent.getStringExtra("alarmType")== "ELAPSED"){
                Intent intentOne = new Intent(context, SampleBootReceiver.class);
                intent.putExtra("alarmType","ELAPSED");
                alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + 2*60*1000,
                        AlarmManager.INTERVAL_DAY, alarmIntent);
                alarmIntent = PendingIntent.getBroadcast(context, 0, intentOne, 0);
                mp=MediaPlayer.create(context, R.raw.alarm   );
                mp.start();

            }
            else if( intent.getStringExtra("alarmType")=="RTC"){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 8);
                calendar.set(Calendar.MINUTE, 30);
                Intent intentOne = new Intent(context, SampleBootReceiver.class);
                intent.putExtra("alarmType","RTC");
                // setRepeating() lets you specify a precise custom interval--in this case,
                // 20 minutes.
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        5*60*1000, alarmIntent);
                alarmIntent = PendingIntent.getBroadcast(context, 0, intentOne, 0);
                mp=MediaPlayer.create(context, R.raw.alarm   );
                mp.start();

            }
            else
            {
                Toast.makeText(context,"INVALID ALARM TYPE",Toast.LENGTH_LONG).show();
            }*/
            //mp=MediaPlayer.create(context, R.raw.alarm   );
            //mp.start();

     //   }
    }
}