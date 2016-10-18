
package com.androidrecipes.mapper;

import com.androidrecipes.mapper.ShapeAdapter.Region;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ShapeMapActivity extends FragmentActivity implements
        RadioGroup.OnCheckedChangeListener,
        ShapeAdapter.OnRegionSelectedListener {

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Check if Google Play Services is up to date.
        switch (GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this)) {
            case ConnectionResult.SUCCESS:
                // Do nothing, move on
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

        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMap = mMapFragment.getMap();

        ShapeAdapter adapter = new ShapeAdapter(mMap);
        adapter.setOnRegionSelectedListener(this);

        adapter.addRectangularRegion("Google HQ",
                new LatLng(37.4168, -122.0890),
                new LatLng(37.4268, -122.0790));
        adapter.addCircularRegion("Neighbor #1",
                new LatLng(37.4118, -122.0740), 400);
        adapter.addCircularRegion("Neighbor #2",
                new LatLng(37.4318, -122.0940), 400);
        
        //Center and zoom map simultaneously
        LatLng mapCenter = new LatLng(37.4218,  -122.0840);
        CameraUpdate newCamera = CameraUpdateFactory.newLatLngZoom(mapCenter, 13);
        mMap.moveCamera(newCamera);
        
        //Wire up the map type selector UI
        RadioGroup typeSelect = (RadioGroup) findViewById(R.id.group_maptype);
        typeSelect.setOnCheckedChangeListener(this);
        typeSelect.check(R.id.type_normal);
    }

    /** OnCheckedChangeListener Methods */
    
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.type_satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.type_normal:
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }
    }

    /** OnRegionSelectedListener Methods */

    @Override
    public void onRegionSelected(Region selectedRegion) {
        Toast.makeText(this, selectedRegion.getName(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoRegionSelected() {
        Toast.makeText(this, "No Region",
                Toast.LENGTH_SHORT).show();
    }
}
