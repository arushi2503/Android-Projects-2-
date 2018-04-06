package com.example.era.fusionmdcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EastGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_east_gallery);
    }

    public void work_specification_btn(View view){
        Intent intent = new Intent(EastGalleryActivity.this,WorkSpecificationActivity.class);
        startActivity(intent);
    }
}
