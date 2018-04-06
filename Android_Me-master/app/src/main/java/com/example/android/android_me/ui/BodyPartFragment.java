package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 02/04/2018.
 */


public class BodyPartFragment extends Fragment {

    private static final String IMAGE_ID_LIST = "image_ids";
    private static final String LIST_INDEX = "list_index";
private static final String TAG = "BodyPartFragment";
    private List<Integer> mImageIds;
    private int mListIndex;

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }


    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState !=null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part,container,false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);
       // imageView.setImageResource(AndroidImageAssets.getAll().get(0));

        if(mImageIds != null){
            imageView.setImageResource(mImageIds.get(mListIndex));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListIndex < mImageIds.size() - 1){
                        mListIndex++;
                    }else{
                        mListIndex = 0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });

        }else{
            Log.v(TAG,"This fragment has null list of image id's");
        }
        return rootView;
    }
    public void onSaveInstanceState(Bundle currentState){

        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX,mListIndex);
    }
}
