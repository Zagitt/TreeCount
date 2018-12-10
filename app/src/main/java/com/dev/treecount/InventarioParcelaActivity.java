package com.dev.treecount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.treecount.model.Parcela;
import com.dev.treecount.model.Person;
import com.google.gson.Gson;

public class InventarioParcelaActivity extends AppCompatActivity {

    Gson gson = new Gson();
    private Button btnNuevoArbol;
    private EditText txtIdParcela;
    private EditText txtIdBrigada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_parcela);

        String data =  getIntent().getExtras().getString("parcela");
        txtIdParcela = (EditText) findViewById(R.id.txtIdParcela);
        txtIdBrigada = (EditText) findViewById(R.id.txtIdBrigada);

        final Parcela parcela = gson.fromJson(data, Parcela.class);
        txtIdParcela.setText("ID: " + parcela.getIdParcela());
        txtIdBrigada.setText("ID: " + parcela.getIdBrigada());

        btnNuevoArbol = (Button) findViewById(R.id.btnNuevoArbol);

        btnNuevoArbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act = new Intent(view.getContext(), DatosEspecieActivity.class);
                act.putExtra("parcela", gson.toJson(parcela));
                view.getContext().startActivity(act);
            }
        });
        /*
        String data =  getIntent().getExtras().getString("person");
        TextView txtNombre = findViewById(R.id.lblNombre);
        TextView txtDni = findViewById(R.id.lblDNI);
        TextView txtEdad = findViewById(R.id.txtEdad);
        TextView lblBiografia = findViewById(R.id.lblBiografia);
        ImageView imgFoto = findViewById(R.id.imgFoto);

        */
    }
}
