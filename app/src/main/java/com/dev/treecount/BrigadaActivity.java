package com.dev.treecount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.treecount.database.BrigadaDBHelper;

public class BrigadaActivity extends AppCompatActivity {
    private ImageView imgFoto;
    private TextView lblNombre;
    private TextView lblDNI;
//    private TextView lblBiografia;
//    private Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miembro_brigada);

        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        lblNombre = (TextView) findViewById(R.id.lblNombre);
        lblDNI = (TextView) findViewById(R.id.lblDNI);
//        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        int vFoto = getIntent().getExtras().getInt("vFoto");
        String vNombre = getIntent().getExtras().getString("vNombre");
        String vDNI = getIntent().getExtras().getString("vDNI");
//        String vBiografia = getIntent().getExtras().getString("vBiografia");

        //Mostrando en pantalla
        imgFoto.setImageResource(vFoto);
        lblNombre.setText(vNombre);
        lblDNI.setText(vDNI);
//        lblBiografia.setText(vBiografia);
        
/*        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarPersona();
            }
        });*/
    }
/*
    private void EliminarPersona() {
        int vId = getIntent().getExtras().getInt("vId");
        CustomersDBHelper db = new CustomersDBHelper(this);
        db.deletePerson(String.valueOf(vId));
        finish();
    }*/
}

