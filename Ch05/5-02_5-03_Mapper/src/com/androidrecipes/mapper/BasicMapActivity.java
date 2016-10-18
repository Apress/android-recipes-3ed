package com.androidrecipes.mapper;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BasicMapActivity extends FragmentActivity implements
        RadioGroup.OnCheckedChangeListener {

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Check if Google Play Services is up to date.
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this)) {
            case ConnectionResult.SUCCESS:
                //Do nothing, move on
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(this,
                        "Maps service requires an update, please open Google Play.",
                        Toast.LENGTH_SHORT).show();
                finish();
                return;
            default:
                Toast.makeText(this,
                        "Maps are not available on this device.",
                        Toast.LENGTH_SHORT).show();
                finish();
                return;
        }
        
        mMapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mMap = mMapFragment.getMap();
        
        //Quickly see if our last known user location is valid, and center
        // the map around that point.  If not, use a default location.
        LocationManager manager =
                (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location =
                manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        LatLng mapCenter;
        if(location != null) {
            mapCenter = new LatLng(location.getLatitude(),
                    location.getLongitude());
        } else {
            //Use a default location
            mapCenter = new LatLng(37.4218,  -122.0840);
        }
        
        //Center and zoom the map simultaneously
        CameraUpdate newCamera = CameraUpdateFactory.newLatLngZoom(mapCenter, 13);
        mMap.moveCamera(newCamera);
        
        // Wire up the map type selector UI
        RadioGroup typeSelect = (RadioGroup) findViewById(R.id.group_maptype);
        typeSelect.setOnCheckedChangeListener(this);
        typeSelect.check(R.id.type_normal);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Enable user location display on the map
        mMap.setMyLocationEnabled(true);
    }
    
    @Override
    public void onPause() {
        super.onResume();
        //Disable user location when not visible
        mMap.setMyLocationEnabled(false);
    }
    
    /** OnCheckedChangeListener Methods */
    
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.type_satellite:
                //Show the satellite map view
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.type_normal:
            default:
                //Show the normal map view
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }
    }
}