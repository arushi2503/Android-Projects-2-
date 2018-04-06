package com.example.era.fusionmdcapp;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {



    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mExtHasChanged = true;
            return false;
        }
    };
    private boolean mExtHasChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView login = (TextView) findViewById(R.id.textview2);
        login.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_extension);
        spinner.setOnTouchListener(mTouchListener);
    }

    public void sign_up_btn(View view){
        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void login(View view){
        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
