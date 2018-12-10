package com.dev.treecount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.dev.treecount.model.Parcela;
import com.google.gson.Gson;

public class DatosEspecieActivity extends AppCompatActivity {

    Gson gson = new Gson();
    private EditText txt_nombre_comun;
    private EditText txt_nombre_scientifico;
    private EditText txt_dap;
    private EditText txt_altura_fuste;
    private EditText txt_altura_total;
    private EditText txt_latitud;
    private EditText txt_longitud;
    private EditText txt_nombre_foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_especie);

        String data =  getIntent().getExtras().getString("parcela");
        final Parcela parcela = gson.fromJson(data, Parcela.class);

        txt_nombre_comun = findViewById(R.id.txt_nombre_comun);
        txt_nombre_scientifico = findViewById(R.id.txt_nombre_scientifico);

        txt_nombre_comun.setText("ID: " + parcela.getIdParcela());
        txt_nombre_scientifico.setText("ID: " + parcela.getIdBrigada());
    }
}
