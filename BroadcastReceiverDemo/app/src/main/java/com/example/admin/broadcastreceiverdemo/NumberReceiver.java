package com.example.admin.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class NumberReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context," Receiver start ", Toast.LENGTH_SHORT).show();
        Log.d("Message ","Receiver starts *******************");

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context,"Ringing State Number is -"+number,Toast.LENGTH_SHORT).show();
            Log.d("NUMBER ","Number is***************************************************: "+number);
            DbHelper dbHelper =new DbHelper(context);
            //SQLiteDatabase database = dbHelper.getWritableDatabase();
            dbHelper.saveNumber(number);
            dbHelper.close();

        }
    }
}
