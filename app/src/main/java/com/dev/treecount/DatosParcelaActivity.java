package com.dev.treecount;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dev.treecount.model.Parcela;
import com.google.gson.Gson;

public class DatosParcelaActivity extends AppCompatActivity {

    private Parcela parcela;
    private int actualBox = 1;

    // BOX 1
    private TextView txtLatitud1;
    private TextView txtLongitud1;

    // BOX 2
    private TextView txtLatitud2;
    private TextView txtLongitud2;

    // BOX 3
    private TextView txtLatitud3;
    private TextView txtLongitud3;

    // BOX 4
    private TextView txtLatitud4;
    private TextView txtLongitud4;

    // Progress
    private LinearLayout lnlProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_parcela);

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
        txtLongitud1 = (TextView) findViewById(R.id.txtLongitud1);
        txtLongitud2 = (TextView) findViewById(R.id.txtLongitud2);
        txtLongitud3 = (TextView) findViewById(R.id.txtLongitud3);
        txtLongitud4 = (TextView) findViewById(R.id.txtLongitud4);

        // Latitudes
        txtLatitud1 = (TextView) findViewById(R.id.txtLatitud1);
        txtLatitud2 = (TextView) findViewById(R.id.txtLatitud2);
        txtLatitud3 = (TextView) findViewById(R.id.txtLatitud3);
        txtLatitud4 = (TextView) findViewById(R.id.txtLatitud4);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualBox = 1;
                refreshLocation();
            }
        });

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualBox = 2;
                refreshLocation();
            }
        });

        btnAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualBox = 3;
                refreshLocation();
            }
        });

        btnAdd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualBox = 4;
                refreshLocation();
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

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

    private void refreshLocation(){

        lnlProgress.setVisibility(View.VISIBLE);

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
        }



       /* final Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setLocation(location);
            }
        }, 3000);*/

    }


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
}
