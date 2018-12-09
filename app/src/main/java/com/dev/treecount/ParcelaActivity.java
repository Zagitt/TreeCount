package com.dev.treecount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dev.treecount.adapter.ParcelaAdapter;
import com.dev.treecount.database.TreeDBHelper;
import com.dev.treecount.model.Parcela;
import com.dev.treecount.services.GetHTTPParcelas;

import java.util.ArrayList;
import java.util.List;

public class ParcelaActivity extends AppCompatActivity {
    private RecyclerView reciclador;
    private List<Parcela> parcelas = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela);

        TreeDBHelper db = new TreeDBHelper(this);
        parcelas = db.getParcelas();

        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        reciclador.setLayoutManager(lManager);

        adapter = new ParcelaAdapter(parcelas);
        reciclador.setAdapter(adapter);
    }
}