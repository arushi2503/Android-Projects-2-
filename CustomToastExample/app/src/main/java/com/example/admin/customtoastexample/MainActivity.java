package com.example.admin.customtoastexample;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button simpleToast, customToast, customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the reference of button
        simpleToast = (Button) findViewById(R.id.simpleToast);
        customToast = (Button) findViewById(R.id.customToast);
        customDialog = (Button) findViewById(R.id.customDialog);

        //perform setonClickListener on custom dialog button
        customDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);

                final EditText email = (EditText) mView.findViewById(R.id.email);
                final EditText pswd = (EditText) mView.findViewById(R.id.password);
                Button login = mView.findViewById(R.id.login);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(!email.getText().toString().isEmpty() && !pswd.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,"Login Successful!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please fill any empty fields!!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        //perform setonClickListener on simple toast button
        simpleToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initiate a Toast with message and duration
                Toast toast = Toast.makeText(getApplicationContext(),"Simple Toast in Android!",Toast.LENGTH_LONG);
                // initiate the Toast with context, message and duration for the Toast
               // toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

        //perform setonClickListener on custom toast button
        customToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.toast_layout_root));
                // get the reference of TextView and ImageVIew from inflated layout
                TextView toastTextView = (TextView) layout.findViewById(R.id.textView);
                ImageView toastImageView = (ImageView) layout.findViewById(R.id.imgView);
                // set the text in the TextView
                toastTextView.setText("Custom Toast In Android");
                // set the Image in the ImageView
                toastImageView.setImageResource(R.drawable.panda);
                // create a new Toast using context
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG); // set the duration for the Toast
                toast.setView(layout); // set the inflated layout
                toast.show(); // display the custom Toast
            }
        });
    }
}
