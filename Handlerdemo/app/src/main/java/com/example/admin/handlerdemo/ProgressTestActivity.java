package com.example.admin.handlerdemo;

import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressTestActivity extends Activity {

    private ProgressBar progress;
    private Button button;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_test);
        progress = findViewById(R.id.progressBar1);
        button = findViewById(R.id.button1);
        text =findViewById(R.id.textView1);
    }
    public  void startProgress(View v){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               for(int i = 0;i <= 10;i++){
                   final int value =i;
                   doFakeWork();
                   progress.post(new Runnable() {
                       @Override
                       public void run() {
                            text.setText("Updating!");
                            progress.setProgress(value);
                       }
                   });
               }
            }

            
        };
          new Thread(runnable).start();
    }

    private void doFakeWork() {
        SystemClock.sleep(5000);
    }
}
