package com.example.googleplacesassignment.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;


import com.example.googleplacesassignment.Data.Pojo.PlacesSearchResponse;
import com.example.googleplacesassignment.Data.Pojo.Result;
import com.example.googleplacesassignment.Di.Components.DaggerMainActivityBindingComponent;
import com.example.googleplacesassignment.Di.Components.GooglePlacesRepoComponent;
import com.example.googleplacesassignment.Di.Modules.MainActivityBindingModule;
import com.example.googleplacesassignment.R;
import com.example.googleplacesassignment.databinding.MainActivityBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {


    MainActivityBinding mainActivityBinding;
    FusedLocationProviderClient location_client;
    LocationCallback locationCallback;
    LocationRequest locationRequest;
    SupportMapFragment mapFragment;
    private final int LOCATION_REQ_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mainActivityBinding = DaggerMainActivityBindingComponent
                .builder()
                .mainActivityBindingModule(new MainActivityBindingModule(this, R.layout.activity_main))
                .build()
                .getMainActivityBinding();

        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_frag);
        mapFragment.getMapAsync(this);

        location_client = LocationServices.getFusedLocationProviderClient(this);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    mainActivityBinding.getLocationObservableFields().locationField.set(location);

                }
            }
        };
        initialize_location_req();

    }

    private void initialize_location_req() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }





    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        final List<Marker> markers = new ArrayList<>();
        mainActivityBinding.getPlaceSearchLiveData().places_search.observe(this, new Observer<PlacesSearchResponse>() {
            @Override
            public void onChanged(PlacesSearchResponse placesSearchResponse) {
                googleMap.clear();
                com.example.googleplacesassignment.Data.Pojo.Location location = null;
                markers.clear();
                for (Result result : placesSearchResponse.getResults()) {
                    location = result.getGeometry().getLocation();
                    markers.add(googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(location.getLat(), location.getLng()))
                            .title(result.getName())
                            .visible(true)));

                }
                if (location != null) {
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.get(0).getPosition(), 10.4f));
                }
            }
        });
        mainActivityBinding.getPlaceSearchLiveData().current_selection.observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(Map<String, Object> stringObjectMap) {
                int pos = (Integer) stringObjectMap.get("pos");
                LatLng location = (LatLng) stringObjectMap.get("location");
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16.4f));
                markers.get(pos).showInfoWindow();
            }

        });

        mainActivityBinding.getPlaceSearchLiveData().unselected.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.get(0).getPosition(), 10.4f));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location_client.requestLocationUpdates(locationRequest, locationCallback, null);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQ_CODE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        location_client.removeLocationUpdates(locationCallback);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    location_client.requestLocationUpdates(locationRequest, locationCallback, null);
                }
                break;

        }
    }
}
