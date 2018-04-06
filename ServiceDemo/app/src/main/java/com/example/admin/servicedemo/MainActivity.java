package com.example.admin.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
   /* public boolean onCreateOptionsmenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    } */

    public void startMethod(View view){
        Intent  i =new Intent(this,SecondService.class);
        i.putExtra("message","This is my message");
        startService(i);
    }
    public void stopMethod(View v){

        Intent  i =new Intent(this,MyService.class);
        stopService(i);
    }
}
