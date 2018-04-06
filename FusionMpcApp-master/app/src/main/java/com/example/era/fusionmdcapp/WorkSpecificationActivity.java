package com.example.era.fusionmdcapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WorkSpecificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_specification);
    }
    public void infrastructure_btn(View view){
        Intent intent = new Intent(WorkSpecificationActivity.this,InfrastructureActivity.class);
        startActivity(intent);
    }
    public void chair_maintainance_btn(View view){
        Intent intent = new Intent(WorkSpecificationActivity.this,ChairMaintainanceActivity.class);
        startActivity(intent);
    }
    public void electricity_btn(View view){
        Intent intent = new Intent(WorkSpecificationActivity.this,ElectricityActivity.class);
        startActivity(intent);
    }
    public void house_keeping_btn(View view){
        Intent intent = new Intent(WorkSpecificationActivity.this,HouseKeepingActivity.class);
        startActivity(intent);
    }
    public void other_issue_btn(View view){
        Intent intent = new Intent(WorkSpecificationActivity.this,AnyOtherIssueActivity.class);
        startActivity(intent);
    }

}
