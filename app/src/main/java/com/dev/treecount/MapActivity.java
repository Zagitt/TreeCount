package com.dev.treecount;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double pLatitud = getIntent().getExtras().getDouble("reflatitud");
        double pLongitud = getIntent().getExtras().getDouble("refLongitud");

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(pLatitud, pLongitud);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Parcela"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
    }
}

