package com.example.projectgame.game;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectgame.R;
import com.example.projectgame.game.GameFragment;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.Task;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maps extends FragmentActivity implements OnMapReadyCallback {
    private GameFragment gameFragment;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private Bundle savedInstanceState;
    private static final LatLng CENTRE = new LatLng(55.75222, 37.6155600);


    public Maps(GameFragment gameFragment, Bundle savedInstanceState){
        this.gameFragment = gameFragment;
        this.savedInstanceState = savedInstanceState;
        fusedLocationProviderClient = LocationServices.
                getFusedLocationProviderClient(this.gameFragment.getActivity());
        fetchLastLocation();
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this.gameFragment.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.gameFragment.getActivity(), new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation(); // во-первых СХС во-вторых измени на mapView
        task.addOnSuccessListener(location -> {
            if (location != null){
                currentLocation = location;
                get_map();
            }
        });
    }

    private void get_map() {
        MapView mView = this.gameFragment.getView().findViewById(R.id.map);
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
    }

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
}