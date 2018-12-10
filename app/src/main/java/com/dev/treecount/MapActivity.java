package com.dev.treecount;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dev.treecount.model.Parcela;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Gson gson = new Gson();
    private Parcela parcela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        String data =  getIntent().getExtras().getString("parcela");
        parcela = gson.fromJson(data, Parcela.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ubicacion1 = new LatLng(Double.parseDouble( Float.toString(parcela.getRefLatitud())), Double.parseDouble( Float.toString(parcela.getRefLongitud())));
        LatLng coordenadas1 = new LatLng(Double.parseDouble( Float.toString(parcela.getP1Latitud())), Double.parseDouble( Float.toString(parcela.getP1Longitud())));
        LatLng coordenadas2 = new LatLng(Double.parseDouble( Float.toString(parcela.getP2Latitud())), Double.parseDouble( Float.toString(parcela.getP2Longitud())));
        LatLng coordenadas3 = new LatLng(Double.parseDouble( Float.toString(parcela.getP3Latitud())), Double.parseDouble( Float.toString(parcela.getP3Longitud())));
        LatLng coordenadas4 = new LatLng(Double.parseDouble( Float.toString(parcela.getP4Latitud())), Double.parseDouble( Float.toString(parcela.getP4Longitud())));
        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(coordenadas1).title("Coordenada #1"));
        mMap.addMarker(new MarkerOptions().position(coordenadas2).title("Coordenada #2"));
        mMap.addMarker(new MarkerOptions().position(coordenadas3).title("Coordenada #3"));
        mMap.addMarker(new MarkerOptions().position(coordenadas4).title("Coordenada #4"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion1,15));
    }
}

