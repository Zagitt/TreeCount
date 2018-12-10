package com.dev.treecount;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.dev.treecount.model.Inventario;
import com.dev.treecount.model.Parcela;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MapArbolActivity extends FragmentActivity implements OnMapReadyCallback {

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
        //String data =  getIntent().getExtras().getString("parcela");
        //parcela = gson.fromJson(data, Parcela.class);

        String dataI =  getIntent().getExtras().getString("parcela");
        parcela = gson.fromJson(dataI, Parcela.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        for(Inventario p: parcela.getInventarios()){
            Double latitud = Double.parseDouble(Float.toString(p.getLat()));
            Double longitud = Double.parseDouble(Float.toString(p.getLon()));
            LatLng coordenada = new LatLng(latitud, longitud);
            mMap.addMarker(new MarkerOptions().position(coordenada).title("Árbol del tipo: "+p.getNom_comun()));
        }


        LatLng ubicacion1 = new LatLng(Double.parseDouble( Float.toString(parcela.getRefLatitud())), Double.parseDouble( Float.toString(parcela.getRefLongitud())));
        LatLng coordenadas1 = new LatLng(Double.parseDouble( Float.toString(parcela.getP1Latitud())), Double.parseDouble( Float.toString(parcela.getP1Longitud())));
        LatLng coordenadas2 = new LatLng(Double.parseDouble( Float.toString(parcela.getP2Latitud())), Double.parseDouble( Float.toString(parcela.getP2Longitud())));
        LatLng coordenadas3 = new LatLng(Double.parseDouble( Float.toString(parcela.getP3Latitud())), Double.parseDouble( Float.toString(parcela.getP3Longitud())));
        LatLng coordenadas4 = new LatLng(Double.parseDouble( Float.toString(parcela.getP4Latitud())), Double.parseDouble( Float.toString(parcela.getP4Longitud())));
        // Add a marker in Sydney and move the camera

        mMap.addMarker(new MarkerOptions().position(coordenadas1).icon(BitmapDescriptorFactory.fromBitmap(changeBitmapColor(0))).title("Coordenada #1"));
        mMap.addMarker(new MarkerOptions().position(coordenadas2).icon(BitmapDescriptorFactory.fromBitmap(changeBitmapColor(0))).title("Coordenada #2"));
        mMap.addMarker(new MarkerOptions().position(coordenadas3).icon(BitmapDescriptorFactory.fromBitmap(changeBitmapColor(0))).title("Coordenada #3"));
        mMap.addMarker(new MarkerOptions().position(coordenadas4).icon(BitmapDescriptorFactory.fromBitmap(changeBitmapColor(0))).title("Coordenada #4"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion1,15));

        PolygonOptions rectOptions = new PolygonOptions().add(coordenadas4,coordenadas1,coordenadas2,coordenadas3,coordenadas4).fillColor(Color.GREEN);

        mMap.addPolygon(rectOptions);
    }

    private Bitmap changeBitmapColor(int color) {

        Bitmap ob = BitmapFactory.decodeResource(this.getResources(), R.drawable.pin_fill);
        Bitmap obm = Bitmap.createBitmap(ob.getWidth(), ob.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap overlay = BitmapFactory.decodeResource(this.getResources(), R.drawable.pin_trans);
        Bitmap overlaym = Bitmap.createBitmap(overlay.getWidth(), overlay.getHeight(), Bitmap.Config.ARGB_8888);


        Canvas canvas = new Canvas(overlaym);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(ob, 0f, 0f, paint);
        canvas.drawBitmap(overlay, 0f, 0f, null);
        return overlaym;
    }
}


