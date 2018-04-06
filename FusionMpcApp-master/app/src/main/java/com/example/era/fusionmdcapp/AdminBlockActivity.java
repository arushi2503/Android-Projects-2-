package com.example.era.fusionmdcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminBlockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_block);
    }
    public void work_specification_btn(View view){
        Intent intent = new Intent(AdminBlockActivity.this,WorkSpecificationActivity.class);
        startActivity(intent);
    }
}
