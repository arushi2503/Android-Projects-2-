package com.example.admin.firstmap;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
   // Circle circle;
    Marker marker;
    Marker marker1;
    Marker marker2;
    Polyline line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMap();
    }

    private void initMap() {
        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        goToLocationZoom(22.750038, 75.895310,15);
    }

    private void goToLocation(double lat, double lng) {
        LatLng ltln = new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ltln);
        mGoogleMap.moveCamera(update);
    }


    private void goToLocationZoom(double lat, double lng, float zoom){
        LatLng ll=  new LatLng(lat,lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mGoogleMap.moveCamera(update);
    }


    public void geoLocate(View v) throws IOException {

        EditText editText = (EditText) findViewById(R.id.editText);
        String location = editText.getText().toString();

        Geocoder geo = new Geocoder(this);   //takes any string & convert into lat &long.
        List<Address> list = geo.getFromLocationName(location,1);
        Address address = list.get(0);
        String locality = address.getLocality();

        Toast.makeText(this,locality, Toast.LENGTH_LONG).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        goToLocationZoom(lat,lng,15);

    //    if(marker != null){
    //        removeMarker();
     //   }

        MarkerOptions options = new MarkerOptions().title(locality).position(new LatLng(lat,lng)).snippet("I'm Here!!");
        if(marker1 == null){
            marker1 = mGoogleMap.addMarker(options);
        }
        else if(marker2 == null){
            marker2 = mGoogleMap.addMarker(options);
            drawLine();
        }
        else{
            removeMarker();
            marker1 = mGoogleMap.addMarker(options);
        }
        marker =  mGoogleMap.addMarker(options);
      //  circle = drawCircle(new LatLng(lat,lng));
    }

    private void drawLine() {
        PolylineOptions options = new PolylineOptions().add(marker1.getPosition(),marker2.getPosition()).color(Color.BLUE).width(3);
        line = mGoogleMap.addPolyline(options);
    }

    //  private Circle drawCircle(LatLng latLng) {
  //      CircleOptions options = new CircleOptions().center(latLng).radius(1000).fillColor(0x33FF0000).strokeColor(Color.BLUE).strokeWidth(3);
   //     return mGoogleMap.addCircle(options);
   // }
   private  void removeMarker(){
       marker1.remove();
       marker1 = null;
       marker2.remove();
       marker2 = null;
       line.remove();
        //circle.remove();
       //circle = null;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mapTypeNone:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case R.id.mapTypeNormal:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.mapTypeSatelite:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.mapTypeHybrid:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.mapTypeterrain:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
