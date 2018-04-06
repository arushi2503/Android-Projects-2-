package com.example.admin.imagegallery;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.GridView;
    import com.example.admin.imagegallery.adapter.GridImageAdapter;
    import com.example.admin.imagegallery.ImageBean;
    import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
        private GridView gridView;
        private GridImageAdapter adapter;
    public static int thumbnail[] = {R.drawable.fashion, R.drawable.groceries, R.drawable.gifts,
                                     R.drawable.baby, R.drawable.home,R.drawable.sports, R.drawable.one,
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
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid_view);
        for (int i = 0; i < thumbnail.length; i++) {
            imageBean = new ImageBean();
            imageBean.setImagePath(thumbnail[i]);
            image_list.add(imageBean);
        }
        adapter = new GridImageAdapter(MainActivity.this, image_list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, FullScreenActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }
}




   /* private GridImageAdapter mImageAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_gallery,container,false);
        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new GridImageAdapter(view.getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(),"Image index: "+position,Toast.LENGTH_LONG).show();


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
        getActivity().setTitle("GalleryFragment");
    }*/
