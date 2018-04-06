package com.example.admin.swiperefreshlayout;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnRefreshListener {

    private SwipeRefreshLayout swipeView;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private String[] LIST_ITEM_TEXT_CITIES = { "Los Angeles", "Chicago",
            "Indianapolis", "San Francisco", "Oklahoma City", "Washington" };

    private String[] LIST_ITEM_TEXT_MORE_CITIES = { "Phoenix", "San Antonio",
            "San Jose", "Nashville", "Las Vegas", "Virginia Beach" };

    private String[] LIST_ITEM_TEXT_EVEN_MORE_CITIES = {"Sans","Texas","Florida","North Carolina" };

   // private List<String> cityList;
    private List<String> cityList;
    // variable to toggle city values on refresh
    boolean refreshToggle = true;

    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_view);
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
                Color.RED, Color.CYAN);
        swipeView.setDistanceToTriggerSync(20);// in dips
        swipeView.setSize(SwipeRefreshLayout.DEFAULT);// LARGE also can be used

        cityList = Arrays.asList(LIST_ITEM_TEXT_CITIES);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.list_item, cityList);
        listView.setAdapter(adapter);
        listView.requestLayout();
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            String[] concatenate = {};
            String[] arr = {};
            concatenate = combine(LIST_ITEM_TEXT_CITIES, LIST_ITEM_TEXT_MORE_CITIES);
            if(refreshToggle && count == 1){
                //refreshToggle = true;

                concatenate = combine(LIST_ITEM_TEXT_CITIES, LIST_ITEM_TEXT_MORE_CITIES);
                adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, concatenate);

            }

            else if (refreshToggle && count == 2) {
                //refreshToggle = false;
                arr = combine(concatenate, LIST_ITEM_TEXT_EVEN_MORE_CITIES);
                Log.d("Concatenate sring : ", "string is ****************" + Arrays.toString(arr));
                adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, arr);

            }
            else {
                refreshToggle = true;
                cityList = Arrays.asList(LIST_ITEM_TEXT_CITIES);
                adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, cityList);
            }

            listView.setAdapter(adapter);

            swipeView.postDelayed(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "city list refreshed", Toast.LENGTH_SHORT).show();
                    swipeView.setRefreshing(false);
                }
            }, 1000);
        };
    };

    @Override
    public void onRefresh() {

        if (count<=3) {
            count++;
        }
        swipeView.postDelayed(new Runnable() {

            @Override
            public void run() {
                swipeView.setRefreshing(true);
                handler.sendEmptyMessage(0);
            }
        }, 1000);
    }

    public String[]
    combine(String[] a, String[] b){
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


}