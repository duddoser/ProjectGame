package com.example.projectgame.game;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.projectgame.R;
import com.example.projectgame.game.GameFragment;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
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

    private List<String> LatLngs;
    private static final LatLng CENTRE = new LatLng(55.75222, 37.6155600);


    public Maps(GameFragment gameFragment){
        this.gameFragment = gameFragment;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.gameFragment.getActivity());
        fetchLastLocation();

        LatLngs = new ArrayList<>();
        LatLngs.add("55.7990012,37.688255");
        LatLngs.add("55.8502375,37.5564886");
        LatLngs.add("55.7081147,37.4315135");
        LatLngs.add("55.5999727,37.6072545");
    }

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
                currentLocation = location;
                Toast.makeText(this.gameFragment.getContext(), (currentLocation.getLatitude()
                        + " " + currentLocation.
                        getLongitude()), Toast.LENGTH_LONG).show();
                get_map();
            }
        });
    }

    public void get_map(){
        MapFragment maps = (MapFragment) this.gameFragment.getActivity().getFragmentManager()
                .findFragmentById(R.id.map);
        maps.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey(mapsApiKey)
                .build();
        draw(googleMap);
//        for (int i=0;i<4;i++){
//            makeResult(geoApiContext, LatLngs.get(i), googleMap);
//        }
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

    public void makeResult(GeoApiContext geoApiContext, String destination, GoogleMap gM){
        List<LatLng> path = new ArrayList<>();
        Log.e("AAAAAAAAAAAAA", "BBBBBBBBBBBB");

        DirectionsApiRequest req = DirectionsApi.getDirections(geoApiContext,
                "55.75222,37.6155600", destination);
        try {
            DirectionsResult res = req.await();
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs !=null) {
                    for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            List<com.google.maps.model.LatLng> coords1 =
                                                    points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                                Log.e("PATH", "ADDED");
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        List<com.google.maps.model.LatLng> coords = points.
                                                decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                            Log.e("PATH", "ADDED");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            Log.e("RESULT ERROR", ex.getLocalizedMessage());
        }
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(R.color.polyline).
                    width(15);
            gM.addPolyline(opts);
        }

    }

    public void draw(GoogleMap gM){
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).
                icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        gM.addMarker(markerOptions);
        //new Coordinates(gM);
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
