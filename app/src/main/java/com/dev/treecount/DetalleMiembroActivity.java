package com.dev.treecount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.treecount.model.Person;
import com.google.gson.Gson;

public class DetalleMiembroActivity extends AppCompatActivity {

    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_miembro);

        String data =  getIntent().getExtras().getString("person");
        TextView txtNombre = findViewById(R.id.lblNombre);
        TextView txtDni = findViewById(R.id.lblDNI);
        TextView txtEdad = findViewById(R.id.txtEdad);
        TextView lblBiografia = findViewById(R.id.lblBiografia);
        ImageView imgFoto = findViewById(R.id.imgFoto);


        if(data != null){
            Person person = gson.fromJson(data, Person.class);
            txtNombre.setText("Miembro: " + person.getNombre());
            txtDni.setText("DNI: " + person.getDni());
        }
    }
}
