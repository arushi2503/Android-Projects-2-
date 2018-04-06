package com.example.admin.xmlpullparserdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);

        XmlPullParserFactory xmlPullParserFactory;
        try {
            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlPullParserFactory.newPullParser();

            InputStream inputStream = getApplicationContext().getAssets().open("sample.xml");
            myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            myParser.setInput(inputStream,null);

            ArrayList<Country> countries = parseXML(myParser);
            String text ="";

            for(Country country : countries){
                text+= "id : "+country.getId()+", name : "+country.getName()+", capital : "+country.getCapital()+"\n";
            }
            textView.setText(text);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Country> parseXML(XmlPullParser myParser) throws XmlPullParserException,IOException {
        ArrayList<Country> countries = null;
        int eventType = myParser.getEventType();
        Country country = null;

        while(eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    countries = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = myParser.getName();
                    if(name.equals("country")){
                        country = new Country();
                        country.id= Integer.parseInt(myParser.getAttributeValue(null,"id"));
                    }else if (country != null){
                        if (name.equals("name")){
                            country.name = myParser.nextText();
                        } else if (name.equals("capital")){
                            country.capital = myParser.nextText();
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = myParser.getName();
                    if (name.equalsIgnoreCase("country") && country != null){
                        countries.add(country);
                    }

            }
            eventType =myParser.next();
        }
        return countries;
    }

}
