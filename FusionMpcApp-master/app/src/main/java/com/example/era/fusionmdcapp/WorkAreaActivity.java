package com.example.era.fusionmdcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WorkAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_area);
    }
    public void north_pavallion_btn(View view){
        Intent intent = new Intent(WorkAreaActivity.this,NorthPavallionActivity.class);
        startActivity(intent);
    }
    public void south_pavallion_btn(View view){
        Intent intent = new Intent(WorkAreaActivity.this,SouthPavallionActivity.class);
        startActivity(intent);
    }
    public void east_gallery_btn(View view){
        Intent intent = new Intent(WorkAreaActivity.this,EastGalleryActivity.class);
        startActivity(intent);
    }
    public void west_gallery_btn(View view){
        Intent intent = new Intent(WorkAreaActivity.this,WestGalleryActivity.class);
        startActivity(intent);
    }
    public void admin_block_btn(View view){
        Intent intent = new Intent(WorkAreaActivity.this,AdminBlockActivity.class);
        startActivity(intent);
    }
}
