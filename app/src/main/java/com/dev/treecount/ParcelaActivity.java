package com.dev.treecount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dev.treecount.model.Parcela;
import com.dev.treecount.services.GetHTTPParcelas;

import java.util.ArrayList;
import java.util.List;

public class ParcelaActivity extends AppCompatActivity {
    private RecyclerView reciclador;
    private List<Parcela> items = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela);

        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        reciclador.setLayoutManager(lManager);

        // esto es temporal hasta implementarlo en el menú
        FillParcela();
    }

    private void FillParcela() {
        GetHTTPParcelas ws = new GetHTTPParcelas(items, reciclador, adapter, this);
        ws.execute();
    }
}

