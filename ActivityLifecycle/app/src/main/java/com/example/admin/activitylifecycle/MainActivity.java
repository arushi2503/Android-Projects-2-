package com.example.admin.activitylifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"On Create --->",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Toast.makeText(MainActivity.this,"On Start --->",Toast.LENGTH_SHORT).show();
    }

    protected void onResume()
    {
        super.onResume();
        Toast.makeText(MainActivity.this,"On Resume --->",Toast.LENGTH_SHORT).show();
    }

    protected void onPause()
    {
        super.onPause();
        Toast.makeText(MainActivity.this,"On Pause --->",Toast.LENGTH_SHORT).show();
    }

    protected void onRestart()
    {
        super.onPause();
        Toast.makeText(MainActivity.this,"On Restart --->",Toast.LENGTH_SHORT).show();
    }

    protected void onStop()
    {
        super.onStop();
        Toast.makeText(MainActivity.this,"On Stop --->",Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Toast.makeText(MainActivity.this,"On Destroy --->",Toast.LENGTH_SHORT).show();
    }
}
