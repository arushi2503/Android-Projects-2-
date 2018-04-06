package com.example.admin.basicuidesign2.galleryadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.basicuidesign2.model.ImageBean;
import com.example.admin.basicuidesign2.R;

import java.util.ArrayList;

public class GridImageAdapter extends BaseAdapter {
    ArrayList<ImageBean> image_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;

    public GridImageAdapter(Context context, ArrayList<ImageBean> image_list) {
        this.context = context;
        this.image_list = image_list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image_list.size();
    }

    @Override
    public Object getItem(int position) {
        return image_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        ImageBean imageBean;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_items, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.imageView);
        } else {
            imageView = (ImageView) convertView.findViewById(R.id.imageView);

        }
        imageBean = (ImageBean) getItem(position);
        Glide.with(context).load(imageBean.getImagePath()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.5f).into(imageView);
        return convertView;
    }
}