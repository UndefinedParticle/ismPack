package com.example.ismpack.ui.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ismpack.R;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationCallback;



public class MapsFragment extends Fragment {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng currentLocation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // 10 seconds

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // Set the map type to hybrid (satellite view with place names)
                mMap.setBuildingsEnabled(true); // Enable 3D buildings
                mMap.getUiSettings().setZoomControlsEnabled(true); // Show zoom controls on the map
                //mMap.setMyLocationEnabled(true);

                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        // Do something on map click

                        // Get the user's current location
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                            fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(LocationResult locationResult) {
                                    if (locationResult == null) {
                                        return;
                                    }
                                    for (Location location : locationResult.getLocations()) {
                                        if (location != null) {
                                            mMap.clear();
                                            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                            mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
                                            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17));
                                        }
                                    }
                                }
                            }, Looper.myLooper());

                        } else {
                            // Request location permission
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        }
                    }
                });

                // Get the user's current location
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                    fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            if (locationResult == null) {
                                return;
                            }
                            for (Location location : locationResult.getLocations()) {
                                if (location != null) {
                                    mMap.clear();
                                    currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                    mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17));
                                }
                            }
                        }
                    }, Looper.myLooper());

                } else {
                    // Request location permission
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
        });


        /*supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // When map is loaded

                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng ismDhanbad) {

                        ismDhanbad = new LatLng(23.81473121008281, 86.4412115344394);
                        googleMap.addMarker(new MarkerOptions().position(ismDhanbad).title("Marker in IIT(ISM)"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ismDhanbad));
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ismDhanbad,17));

                    }
                });
            }
        });*/

        return view;
    }

}
