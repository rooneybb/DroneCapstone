package com.example.medfleet.med_fleet;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class OpenMap extends FragmentActivity implements LocationListener {

    GoogleMap googleMap;
    int severity;
    double latitude, longitude;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check Google Play Services
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }

        //Set Strict Mode for allowing network connections
        {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        //Retrieve the severity reading from the previous Activity and store
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        severity = Integer.parseInt(message);


        //Set Activity View
        setContentView(R.layout.view_map);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                //TO-DO Implement permission checks
        }

        //Begin Location Retrieving Logic
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        googleMap = supportMapFragment.getMap();

        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        String bestProvider = locationManager.getBestProvider(criteria, true);
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);

        Location location = locationManager.getLastKnownLocation(bestProvider);

        //Emulator returns null location so we hard code to Soldier Field
        if(location == null)
        {
            location = new Location("");
            location.setLatitude(41.862125d);
            location.setLongitude(-87.616787d);
        }
        //Set initial LatLong
        setLatLong(location.getLatitude(), location.getLongitude());

        //Define LatLong Obj
        LatLng latLng = new LatLng(getLat(), getLong());

        //Set initial Text View
        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
        locationTv.setText("Latitude:" + getLat() + ", Longitude:" + getLong());

        //Zoom To Current Location
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //Drop Marker at initial position
        final Marker mapMarker = googleMap.addMarker(new MarkerOptions()
            .position(latLng)
            .draggable(true));

        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDrag(Marker arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onMarkerDragEnd(Marker arg0) {
                // TODO Auto-generated method stub
                LatLng markerLocation = mapMarker.getPosition();
                setLatLong(markerLocation.latitude, markerLocation.longitude);

                TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
                locationTv.setText("Latitude:" + getLat() + ", Longitude:" + getLong());
            }

            @Override
            public void onMarkerDragStart(Marker arg0) {
                // TODO Auto-generated method stub
            }
        });

        if (location != null) {
            onLocationChanged(location);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        //Our App doesn't do anything when the users location changes
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    public void setLatLong(double _lat, double _long) {
        latitude = _lat;
        longitude = _long;
    }

    public double getLat()
    {
        return latitude;
    }

    public double getLong()
    {
        return longitude;
    }

    public void submitClicked(View view){

        new AlertDialog.Builder(this)
                .setTitle("Are You Sure?")
                .setMessage(R.string.alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendRequest();
                    }
                })
                .setNegativeButton(("No"), null)
                .show();
    }

    public final void sendRequest()
    {
        try {
            String urlStr = getString(R.string.urlPost);
            URL url = new URL(urlStr);
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", "android.schoolportal.gr");
            connection.setUseCaches (false);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("latitude", latitude);
            jsonParam.put("longitude", longitude);
            jsonParam.put("urgency", severity);

            wr.writeBytes(jsonParam.toString());
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            if (responseCode == 200) {
                Intent intent = new Intent(this, Finish.class);
                intent.putExtra("result", "true");
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(this, Finish.class);
                intent.putExtra("result", "false");
                startActivity(intent);
            }
        }
        catch(java.io.IOException e){
            System.out.println(e.getMessage());
            Intent intent = new Intent(this, Finish.class);
            intent.putExtra("result", "false");
            startActivity(intent);
        }
        catch(org.json.JSONException e){
            System.out.println(e.getMessage());
            Intent intent = new Intent(this, Finish.class);
            intent.putExtra("result", "false");
            startActivity(intent);
        }
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }
}
