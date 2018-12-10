package com.dev.treecount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.treecount.adapter.InventarioAdapter;
import com.dev.treecount.database.TreeDBHelper;
import com.dev.treecount.model.Inventario;
import com.dev.treecount.model.Parcela;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class InventarioParcelaActivity extends AppCompatActivity {

    private RecyclerView reciclador;
    Gson gson = new Gson();
    private Button btnNuevoArbol;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private EditText txtIdParcela;
    private EditText txtIdBrigada;
    private List<Inventario> inventario = new ArrayList<>();

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


        TreeDBHelper db = new TreeDBHelper(this);
        inventario = db.getInventario(parcela.getIdParcela());

        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        reciclador.setLayoutManager(lManager);

        adapter = new InventarioAdapter(getApplicationContext(), inventario);
        reciclador.setAdapter(adapter);


        btnNuevoArbol = (Button) findViewById(R.id.btnNuevoArbol);

        btnNuevoArbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act = new Intent(view.getContext(), DatosEspecieActivity.class);
                act.putExtra("parcela", gson.toJson(parcela));
                view.getContext().startActivity(act);
            }
        });
    }
}
