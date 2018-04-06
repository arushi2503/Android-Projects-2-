package com.example.admin.recievedatajson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button click;
    public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click= (Button) findViewById(R.id.button);
        data = (TextView) findViewById(R.id.text);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FetchData process = new FetchData();
                process.execute();
            }
        });
    }
}















/*  https://api.myjson.com/bins/13u1r9  */