package com.example.admin.recievedatajson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class FetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    @Override
    //this is the background thread.

    protected Void doInBackground(Void... voids) {
        try {
            URL url =  new URL(" https://api.myjson.com/bins/13u1r9");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = con.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data +line;
            }

            //To parse the data
            JSONArray ja = new JSONArray(data);
            for(int i=0; i<ja.length(); i++){
                JSONObject jo = (JSONObject) ja.get(i);
                singleParsed = "Name: " + jo.get("Name")+ "\n" +
                               "Password: " + jo.get("Password")+ "\n" +
                               "Country: " + jo.get("Country")+ "\n" ;

                dataParsed = dataParsed + singleParsed + "\n";

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //this is the ui thread. after the background thred has been done, you can change something in the ui

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);
    }
}
