package com.example.admin.basiclayoutscreen2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 21/02/2018.
 */
public class FirstFragment extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView =inflater.inflate(R.layout.first_layout,container,false);
        return myView;
    }
}
