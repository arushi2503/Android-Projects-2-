package com.example.admin.basicuidesign2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_frame,fragment);
        ft.replace(R.id.content_frame,fragment);
        ft.commit();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        Fragment fragment = null;
                        switch (id) {
                            case R.id.nav_gallery:
                                //Intent i = new Intent(MainActivity.this,GalleryActivity.class);
                                //startActivity(i);
                                fragment = new GalleryFragment();
                                break;

                            case R.id.nav_addContact:
                                Intent i1 = new Intent(MainActivity.this,ShowContactActivity.class);
                                startActivity(i1);
                                break;

                            case R.id.nav_showContacts:
                               //fragment = new SearchContactActivity();
                                Intent intent = new Intent(MainActivity.this,SearchContactActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_yourlocation:
                                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                                startActivity(i);
                                break;

                        }
                        if(fragment != null){
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content_frame,fragment);
                            ft1.commit();
                        }

                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        return true;
                    }
                });


    }
   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // THIS IS YOUR DRAWER/HAMBURGER BUTTON
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
