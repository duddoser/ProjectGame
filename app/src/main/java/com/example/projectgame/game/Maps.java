package com.example.projectgame.game;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.projectgame.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

/* All GoogleMap functions are defined here. */
public class Maps extends FragmentActivity implements OnMapReadyCallback {
    private GameFragment gameFragment;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private Bundle savedInstanceState;
    private static final LatLng CENTRE = new LatLng(55.75222, 37.6155600);
    double longitude, latitude;
    static final double LNG = 37.6156049;
    static final double LTD = 55.7565494;
    private MapView mView;


    public Maps(GameFragment gameFragment, Bundle savedInstanceState){
        this.gameFragment = gameFragment;
        this.savedInstanceState = savedInstanceState;
        fusedLocationProviderClient = LocationServices.
                getFusedLocationProviderClient(this.gameFragment.getActivity());
        fetchLastLocation();
    }

    //here we get currentLocation
    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this.gameFragment.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.gameFragment.getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null){
                this.longitude = location.getLongitude();
                this.latitude = location.getLatitude();
                currentLocation = location;
                get_map();
            }
        });
    }

    public Location get_location(){
        return currentLocation;
    }
    private void get_map() {
        mView = this.gameFragment.getView().findViewById(R.id.map);
        mView.onCreate(savedInstanceState);
        mView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        draw(googleMap);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CENTRE, 10));

        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this.gameFragment.getContext(), R.raw.style_json));
            if (!success) {
                Log.e("MAP STYLE", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MAPS RESOURCES", "Can't find style. Error: ", e);
        }
        mView.onResume();
    }

    //here we customize marker
    public void draw(GoogleMap gM){
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).
                icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        gM.addMarker(markerOptions);
        new Coordinates(gM, this.gameFragment.getContext());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED){
                    fetchLastLocation(); }
                break;
        }

    }

    //and here we get the type of resource user can get
    public String getDistrict(){
        if (this.latitude >= LTD && this.longitude <= LNG){
            return "wood";
        } else if (this.latitude >= LTD && this.longitude >= LNG) {
            return "stone";
        } else if (this.latitude <= LTD && this.longitude >= LNG){
            return "metal";
        } else if (this.latitude <= LTD && this.longitude <= LNG){
            return "iron";
        }
        return "stone";
    }
}
