package com.star.whereami;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WhereAmIActivity extends AppCompatActivity {

    private TextView mMyLocationTextView;

    private LocationManager mLocationManager;

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whereami);

        mMyLocationTextView = (TextView) findViewById(R.id.my_location_text_view);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        updateWithNewLocation(location);

        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 15000, 0,
                mLocationListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLocationManager.removeUpdates(mLocationListener);
    }

    private void updateWithNewLocation(Location location) {

        String latLngString = "No location found";

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            latLngString = "Latitude: " + latitude + "\n" + "Longitude: " + longitude;
        }

        mMyLocationTextView.setText("Your Current Position is: " + "\n" + latLngString);
    }

}
