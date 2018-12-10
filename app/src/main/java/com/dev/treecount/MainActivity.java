package com.dev.treecount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnBienvendo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBienvendo = (Button) findViewById(R.id.btn_bienvenido);

        Toast.makeText(this, "Bienvenido al Sistema de Inventario Forestal", Toast.LENGTH_SHORT).show();

        btnBienvendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(v.getContext(), ParcelaActivity.class);
            }
        });

    }
}
