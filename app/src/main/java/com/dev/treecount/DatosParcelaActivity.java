package com.dev.treecount;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.treecount.database.TreeDBHelper;
import com.dev.treecount.model.Parcela;
import com.dev.treecount.util.GeoLocation;
import com.google.gson.Gson;

public class DatosParcelaActivity extends AppCompatActivity {
    private GeoLocation geoLocation;
    private Parcela parcela;

    // BOX 1
    private EditText txtLatitud1;
    private EditText txtLongitud1;
    // BOX 2
    private EditText txtLatitud2;
    private EditText txtLongitud2;
    // BOX 3
    private EditText txtLatitud3;
    private EditText txtLongitud3;
    // BOX 4
    private EditText txtLatitud4;
    private EditText txtLongitud4;
    // Progress
    private LinearLayout lnlProgress;

    private Button btnGuardar;
    private Button btnMostrarMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_parcela);

        geoLocation = new GeoLocation(this);

        String data = getIntent().getExtras().getString("parcela");
        parcela = new Gson().fromJson(data, Parcela.class);
        getSupportActionBar().setTitle(parcela.getNombre());

        lnlProgress = (LinearLayout) findViewById(R.id.lnlProgress);

        // Botones
        FloatingActionButton btnAdd1 = (FloatingActionButton) findViewById(R.id.btnAdd1);
        FloatingActionButton btnAdd2 = (FloatingActionButton) findViewById(R.id.btnAdd2);
        FloatingActionButton btnAdd3 = (FloatingActionButton) findViewById(R.id.btnAdd3);
        FloatingActionButton btnAdd4 = (FloatingActionButton) findViewById(R.id.btnAdd4);

        // Longitudes
        txtLongitud1 = (EditText) findViewById(R.id.txtLongitud1);
        txtLongitud2 = (EditText) findViewById(R.id.txtLongitud2);
        txtLongitud3 = (EditText) findViewById(R.id.txtLongitud3);
        txtLongitud4 = (EditText) findViewById(R.id.txtLongitud4);

        // Latitudes
        txtLatitud1 = (EditText) findViewById(R.id.txtLatitud1);
        txtLatitud2 = (EditText) findViewById(R.id.txtLatitud2);
        txtLatitud3 = (EditText) findViewById(R.id.txtLatitud3);
        txtLatitud4 = (EditText) findViewById(R.id.txtLatitud4);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnMostrarMapa = (Button) findViewById(R.id.btnMostrarMapa);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geoLocation.getTextCoordinates(txtLongitud1, txtLatitud1, null, null);
            }
        });

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geoLocation.getTextCoordinates(txtLongitud2, txtLatitud2, null, null);
            }
        });

        btnAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geoLocation.getTextCoordinates(txtLongitud3, txtLatitud3, null, null);
            }
        });

        btnAdd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geoLocation.getTextCoordinates(txtLongitud4, txtLatitud4, null, null);
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardaParcela();
            }
        });

        btnMostrarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostrarMapa();
            }
        });

    }

    private void MostrarMapa() {
        if (txtLatitud1.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese latitud", Toast.LENGTH_SHORT).show();
            return;
        }
        if (txtLongitud1.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese longitud", Toast.LENGTH_SHORT).show();
            return;
        }
        double pLatitud = Double.parseDouble(txtLatitud1.getText().toString());
        double pLongitud = Double.parseDouble(txtLongitud1.getText().toString());

        // Crear paquete de datos
        Bundle bundle = new Bundle();
        bundle.putDouble("pLatitud", pLatitud);
        bundle.putDouble("pLongitud", pLongitud);

        Intent activity = new Intent(this, MapActivity.class);
        activity.putExtras(bundle);
        startActivity(activity);
    }

    private void GuardaParcela() {
        ContentValues values = new ContentValues();
        float txtLatitud;
        float txtLongitud;

        if(txtLatitud1.getText().toString().isEmpty()) txtLatitud = 1; else txtLatitud = Float.parseFloat(txtLatitud1.getText().toString());
        values.put("p1_latitud", txtLatitud);
        if(txtLongitud1.getText().toString().isEmpty()) txtLongitud = 1; else txtLongitud = Float.parseFloat(txtLongitud1.getText().toString());
        values.put("p1_longitud", txtLongitud);
        if(txtLatitud2.getText().toString().isEmpty()) txtLatitud = 1; else txtLatitud = Float.parseFloat(txtLatitud2.getText().toString());
        values.put("p2_latitud", txtLatitud);
        if(txtLongitud2.getText().toString().isEmpty()) txtLongitud = 1; else txtLongitud = Float.parseFloat(txtLongitud2.getText().toString());
        values.put("p2_longitud", txtLongitud);
        if(txtLatitud3.getText().toString().isEmpty()) txtLatitud = 1; else txtLatitud = Float.parseFloat(txtLatitud3.getText().toString());
        values.put("p3_latitud", txtLatitud);
        if(txtLongitud3.getText().toString().isEmpty()) txtLongitud = 1; else txtLongitud = Float.parseFloat(txtLongitud3.getText().toString());
        values.put("p3_longitud", txtLongitud);
        if(txtLatitud4.getText().toString().isEmpty()) txtLatitud = 1; else txtLatitud = Float.parseFloat(txtLatitud4.getText().toString());
        values.put("p4_latitud", txtLatitud);
        if(txtLongitud4.getText().toString().isEmpty()) txtLongitud = 1; else txtLongitud = Float.parseFloat(txtLongitud4.getText().toString());
        values.put("p4_longitud", txtLongitud);

        TreeDBHelper db = new TreeDBHelper(this);
        db.updateParcela(values, parcela.getIdParcela());
        Toast.makeText(this, "Registro guardado satisfactoriamente!", Toast.LENGTH_SHORT).show();
    }

    /*
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            lnlProgress.setVisibility(View.GONE);
            setLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    */

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Debes aceptar los permisos de localización...", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    /*
    private void refreshLocation(){

        lnlProgress.setVisibility(View.VISIBLE);

        geoLocation.getTextCoordinates(txtLongitud, txtLatitud, null, null);

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
        }

        final Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setLocation(location);
            }
        }, 3000);
    }
    */


    /*
    private void setLocation(Location location){
        switch (actualBox){
            case 1:
                txtLatitud1.setText(String.valueOf(location.getLatitude()));
                txtLongitud1.setText(String.valueOf(location.getLongitude()));
                break;
            case 2:
                txtLatitud2.setText(String.valueOf(location.getLatitude()));
                txtLongitud2.setText(String.valueOf(location.getLongitude()));
                break;
            case 3:
                txtLatitud3.setText(String.valueOf(location.getLatitude()));
                txtLongitud3.setText(String.valueOf(location.getLongitude()));
                break;
            case 4:
                txtLatitud4.setText(String.valueOf(location.getLatitude()));
                txtLongitud4.setText(String.valueOf(location.getLongitude()));
                break;
        }
    }
    */

}
