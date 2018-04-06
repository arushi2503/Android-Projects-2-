package com.example.admin.basicuidesign2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.admin.basicuidesign2.galleryadapter.GridImageAdapter;
import com.example.admin.basicuidesign2.model.ImageBean;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GridImageAdapter adapter;

    public static int thumbnail[] = {R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
            R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6,
            R.drawable.sample_7, R.drawable.sample_8, R.drawable.sample_9, R.drawable.sample_10,
            R.drawable.sample_11, R.drawable.sample_12, R.drawable.sample_13, R.drawable.sample_14,
            R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five,
            R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine,
            R.drawable.ten, R.drawable.eleven, R.drawable.twelve, R.drawable.thirteen,
            R.drawable.fourteen, R.drawable.fifteen, R.drawable.sixteen, R.drawable.seventeen,
            R.drawable.eighteen, R.drawable.nineteen, R.drawable.twenty, R.drawable.twentyone,
            R.drawable.twentytwo, R.drawable.twentytwo, R.drawable.twentythree,
            R.drawable.twentyfour, R.drawable.twentyfive, R.drawable.twentysix};

    public static ArrayList<ImageBean> image_list = new ArrayList<>();
    ImageBean imageBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.grid);

        for (int i = 0; i < thumbnail.length; i++) {
            imageBean = new ImageBean();
            imageBean.setImagePath(thumbnail[i]);
            image_list.add(imageBean);
        }
        GridImageAdapter adapter = new GridImageAdapter(getContext(), image_list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "Image index: " + position, Toast.LENGTH_LONG).show();

                Intent i = new Intent(getActivity(), FullScreenActivity.class);
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // getActivity().setTitle("GalleryFragment");
    }
}





/* public class GalleryActivity extends AppCompatActivity {

    //private GridImageAdapter adapter;

    public static int thumbnail[] = { R.drawable.sample_0,R.drawable.sample_1,R.drawable.sample_2,
            R.drawable.sample_3,R.drawable.sample_4,R.drawable.sample_5,R.drawable.sample_6,
            R.drawable.sample_7,R.drawable.sample_8,R.drawable.sample_9,R.drawable.sample_10,
            R.drawable.sample_11,R.drawable.sample_12,R.drawable.sample_13,R.drawable.sample_14,
            R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five,
            R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine,
            R.drawable.ten, R.drawable.eleven, R.drawable.twelve, R.drawable.thirteen,
            R.drawable.fourteen, R.drawable.fifteen, R.drawable.sixteen, R.drawable.seventeen,
            R.drawable.eighteen,R.drawable.nineteen,R.drawable.twenty,R.drawable.twentyone,
            R.drawable.twentytwo,R.drawable.twentytwo,R.drawable.twentythree,
            R.drawable.twentyfour,R.drawable.twentyfive,R.drawable.twentysix};
    public static ArrayList<ImageBean> image_list = new ArrayList<>();
    ImageBean imageBean;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.customAction.fragment_gallery);
       // gridView = (GridView) findViewById(R.id.grid);

        GridView gridView = (GridView) findViewById(R.id.grid);
        for (int i = 0; i < thumbnail.length; i++) {
            imageBean = new ImageBean();
            imageBean.setImagePath(thumbnail[i]);
            image_list.add(imageBean);
        }
        GridImageAdapter adapter = new GridImageAdapter(GalleryActivity.this,image_list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GalleryActivity.this, FullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }
}*/
