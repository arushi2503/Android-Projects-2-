package com.example.admin.multithreading;
    import android.app.Application;
    import android.util.Log;
    import android.widget.Toast;

public class AndroidApplication extends Application{
        private final static String TAG = "Android Application";

        @Override
        public void onCreate(){

          super.onCreate();
            Log.i(TAG,"Application, On Create!");
            Toast.makeText(this,"Application, On Create",Toast.LENGTH_LONG).show();
        }

    public class SecondThread extends Thread{
        public void run(){
            try {
                Thread.sleep(6 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    }
