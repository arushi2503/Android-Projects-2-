package com.example.admin.basicuidesign2.galleryadapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.admin.basicuidesign2.model.ImageBean;
import com.example.admin.basicuidesign2.R;
import com.example.admin.basicuidesign2.utils.TouchImageView;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    ArrayList<ImageBean> image_list = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    TouchImageView imageDisplay;

    public ViewPagerAdapter(ArrayList<ImageBean> image_list, Context context) {
        this.image_list = image_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image_list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View viewLayout = layoutInflater.inflate(R.layout.image_slider, container, false);
        imageDisplay = (TouchImageView) viewLayout.findViewById(R.id.full_image);
        Glide.with(context).load(image_list.get(position).getImagePath()).placeholder(R.drawable.wait).into(imageDisplay);
        ((ViewPager) container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}