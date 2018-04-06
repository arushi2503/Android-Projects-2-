package com.example.admin.basicuidesign2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private final Activity context;
    private ArrayList<BrowserBean> beanArrayList;

    public CustomListAdapter(Activity context,ArrayList<BrowserBean> beanArrayList) {
        this.context = context;
        this.beanArrayList = beanArrayList;
    }
    @Override
    public int getCount() {
        return beanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return  beanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row, null);
        }


        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt1);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        TextView extratxt = (TextView) convertView.findViewById(R.id.txt2);

        txtTitle.setText(beanArrayList.get(position).getBrowseTitle());
        extratxt.setText(beanArrayList.get(position).getBrowseTitle());
        imageView.setImageResource(beanArrayList.get(position).getBrowseImage());

        return convertView;
    }


}

