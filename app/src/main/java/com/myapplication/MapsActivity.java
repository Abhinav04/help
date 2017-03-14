package com.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.messaging.SendException;

public class MapsActivity extends Activity implements LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    protected String latitude,longitude;
    protected boolean gps_enabled,network_enabled;


    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static Garbage newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Garbage fragment = new Garbage();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        //mPage = getArguments().getInt(ARG_PAGE);
       // setUpMapIfNeeded();
    }




    @Override
    public void onLocationChanged(Location location) {

        Toast.makeText(getApplicationContext(),location.getLatitude() + " Longitude:" + location.getLongitude(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");

    }

    @Override
    public void onProviderEnabled(String provider) {

        Log.d("Latitude","ensable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

/*
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
*/
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
  /*  private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
*/
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
   /* private void setUpMap() {
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }*/

}
