package com.example.admin.asynctaskdemo;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReadWebPageAsyncTask extends Activity {
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(urls[0])
                            .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.isSuccessful()) {
                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return"Download failed";
        }


    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
    }

}
    // Triggered via a button in your layout
    public void onClick(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[]{"http://www.vogella.com/index.html"});
    }

    public void onClickHandler(View view){
        Runnable r = new Runnable() {
            @Override
            public void run() {
              long futureTime = System.currentTimeMillis()+10000;
                while(System.currentTimeMillis() < futureTime){
                    synchronized (this){
                        try {
                            wait(futureTime - System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Thread thread = new Thread(r);
        thread.start();

        textView.setText("Good Job Folks!");

    }
}