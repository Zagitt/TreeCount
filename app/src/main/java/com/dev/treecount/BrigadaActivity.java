package com.dev.treecount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.dev.treecount.adapter.PersonAdapter;
import com.dev.treecount.database.TreeDBHelper;
import com.dev.treecount.model.Person;

import java.util.ArrayList;
import java.util.List;

public class BrigadaActivity extends AppCompatActivity {
    private RecyclerView reciclador;
    private List<Person> personas = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Button btnParcelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miembro_brigada);

        TreeDBHelper db = new TreeDBHelper(this);
        personas = db.getPersonas();

        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        reciclador.setLayoutManager(lManager);

        adapter = new PersonAdapter(getApplicationContext(),personas);
        reciclador.setAdapter(adapter);


        btnParcelas = (Button) findViewById(R.id.btnParcelas);

        btnParcelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act = new Intent(getApplicationContext(), ParcelaActivity.class);
                startActivity(act);
            }
        });
    }
}

