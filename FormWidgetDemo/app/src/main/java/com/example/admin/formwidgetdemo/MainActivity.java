package com.example.admin.formwidgetdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText edittext;
    CheckBox checkbox;
    RatingBar ratingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Clicked!!!!!",Toast.LENGTH_LONG).show();
            }
        });
        edittext = (EditText) findViewById(R.id.edittext);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN)&& keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    Toast.makeText(MainActivity.this,edittext.getText(),Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        checkbox =(CheckBox) findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()){
                    Toast.makeText(MainActivity.this,"Checked!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Not Checked!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        View.OnClickListener radio_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                Toast.makeText(MainActivity.this,rb.getText(),Toast.LENGTH_SHORT).show();
            }
        };

        RadioButton radio_red = (RadioButton)findViewById(R.id.radio_red);
        RadioButton radio_blue = (RadioButton)findViewById(R.id.radio_blue);
        radio_red.setOnClickListener(radio_listener);
        radio_blue.setOnClickListener(radio_listener);

        ratingbar =(RatingBar)findViewById(R.id.ratingbar);
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this,"New Rating: "+rating,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
