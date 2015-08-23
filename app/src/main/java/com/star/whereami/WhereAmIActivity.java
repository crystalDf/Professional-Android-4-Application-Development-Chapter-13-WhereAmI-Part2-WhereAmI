package com.star.whereami;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WhereAmIActivity extends AppCompatActivity {

    private TextView mMyLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whereami);

        mMyLocationTextView = (TextView) findViewById(R.id.my_location_text_view);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        updateWithNewLocation(location);
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
