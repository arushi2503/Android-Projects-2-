package com.example.admin.signuplayout;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_main);

        TextView login = (TextView) findViewById(R.id.textview2);
        login.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_extension);
        spinner.setOnTouchListener(mTouchListener);



    }



}
