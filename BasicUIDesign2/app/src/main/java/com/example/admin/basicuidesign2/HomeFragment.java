package com.example.admin.basicuidesign2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private CustomListAdapter adapter;
    private ArrayList<BrowserBean> browserBeenList;
    ListView browserList ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        browserList = (ListView) view.findViewById(R.id.browserlistView);

        initializeList();

        adapter = new CustomListAdapter(getActivity(), browserBeenList);
        //ADAPTER
        browserList.setAdapter(adapter);

        browserList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete: " + position);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        browserBeenList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Item Deleted: " + position, Toast.LENGTH_LONG).show();
                    }
                });
                adb.show();

                return true;
            }
        });
        return view;
    }

    private void initializeList() {

        browserBeenList = new ArrayList<>();

        BrowserBean browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.safari);
        browserBean.setBrowseTitle("Safari");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.chrome);
        browserBean.setBrowseTitle("Chrome");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.opera);
        browserBean.setBrowseTitle("Opera");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.firefox);
        browserBean.setBrowseTitle("FireFox");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.uc);
        browserBean.setBrowseTitle("UC Browser");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.maxthon);
        browserBean.setBrowseTitle("maxthon");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.facebook);
        browserBean.setBrowseTitle("facebook");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.insta);
        browserBean.setBrowseTitle("Instagram");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.whatsapp);
        browserBean.setBrowseTitle("Whatsapp");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.snap);
        browserBean.setBrowseTitle("Snapchat");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.angellist);
        browserBean.setBrowseTitle("AngleList");
        browserBeenList.add(browserBean);

        browserBean = new BrowserBean();
        browserBean.setBrowseDetail("Description");
        browserBean.setBrowseImage(R.drawable.linkedin);
        browserBean.setBrowseTitle("Linkedin");
        browserBeenList.add(browserBean);

    }

}
