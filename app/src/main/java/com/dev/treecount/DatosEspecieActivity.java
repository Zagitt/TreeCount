package com.dev.treecount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dev.treecount.database.TreeDBHelper;
import com.dev.treecount.model.ArbolTipo;
import com.dev.treecount.model.Inventario;
import com.dev.treecount.model.Parcela;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DatosEspecieActivity extends AppCompatActivity {

    private List<ArbolTipo> arbolesTipo = new ArrayList<>();
    Gson gson = new Gson();

    private Spinner spn_nombre_comun;
    private EditText txtIdParcela;
    private EditText txtIdBrigada;
    private EditText txt_dap;
    private EditText txt_altura_fuste;
    private EditText txt_altura_total;
    private EditText txt_latitud;
    private EditText txt_longitud;
    private Button btnNuevoArbol;
    List<String> comboData = new ArrayList<>();
    List<String> comboNombre = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_especie);

        spn_nombre_comun = (Spinner) findViewById(R.id.spn_nombre_comun);
        txtIdParcela = (EditText) findViewById(R.id.txtIdParcela);
        txtIdBrigada = (EditText) findViewById(R.id.txtIdBrigada);
        txt_dap = (EditText) findViewById(R.id.txt_dap);
        txt_altura_fuste = (EditText) findViewById(R.id.txt_altura_fuste);
        txt_altura_total = (EditText) findViewById(R.id.txt_altura_total);
        txt_latitud = (EditText) findViewById(R.id.txt_latitud);
        txt_longitud = (EditText) findViewById(R.id.txt_longitud);


        String data =  getIntent().getExtras().getString("parcela");
        final Parcela parcela = gson.fromJson(data, Parcela.class);
        txtIdParcela.setText(""+parcela.getIdParcela());
        txtIdBrigada.setText(""+parcela.getIdBrigada());

        TreeDBHelper db = new TreeDBHelper(this);
        arbolesTipo = db.getArbolesTipo();
        for (ArbolTipo p : arbolesTipo) {
            comboData.add(p.getNombre_comun());
            comboNombre.add(p.getNombre_cientifico());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, comboData
        );
        spn_nombre_comun.setAdapter(dataAdapter);
        spn_nombre_comun.setSelection(0);



        btnNuevoArbol = (Button) findViewById(R.id.btnNuevoArbol);
        btnNuevoArbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarInventario();
            }
        });
    }

    private void GuardarInventario() {
        if(txt_dap.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese DAP", Toast.LENGTH_SHORT).show();
            return;
        }else if(txt_dap.getText().toString().length() > 45){
            int num = txt_dap.getText().toString().length() - 45;
            Toast.makeText(this, "Excedió el número de caracteres máximo"+"(Excedió en:"+num+")", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txt_altura_fuste.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese altura del fuste del árbol", Toast.LENGTH_SHORT).show();
            return;
        }else if(txt_altura_fuste.getText().toString().length() > 11){
            Toast.makeText(this, "Ingrese altura del fuste válida", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txt_altura_total.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese altura total del árbol", Toast.LENGTH_SHORT).show();
            return;
        }else if(txt_altura_total.getText().toString().length() > 11){
            Toast.makeText(this, "Ingrese altura total válida", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txt_latitud.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese latitud de coordenada", Toast.LENGTH_SHORT).show();
            return;
        }else if(txt_latitud.getText().toString().length() > 11){
            Toast.makeText(this, "Ingrese latitud válida", Toast.LENGTH_SHORT).show();
            return;
        }
        if(txt_longitud.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese longitud de coordenada", Toast.LENGTH_SHORT).show();
            return;
        }else if(txt_longitud.getText().toString().length() > 11){
            Toast.makeText(this, "Ingrese longitud válida", Toast.LENGTH_SHORT).show();
            return;
        }


        int selectedItem = spn_nombre_comun.getSelectedItemPosition();
        String nom_cientifico = comboNombre.get(selectedItem);
        String nom_comun = comboData.get(selectedItem);
        float lat = Float.parseFloat(txt_latitud.getText().toString());
        float lon = Float.parseFloat(txt_longitud.getText().toString());
        String dap = txt_dap.getText().toString();
        int altura_fuste = Integer.parseInt(txt_altura_fuste.getText().toString());
        int altura_total = Integer.parseInt(txt_altura_total.getText().toString());
        int idParcela = Integer.parseInt(txtIdParcela.getText().toString());

        Inventario item = new Inventario(nom_cientifico,nom_comun,lat,lon,dap,altura_fuste,altura_total,idParcela);

        TreeDBHelper db = new TreeDBHelper(this);
        db.saveInventario(item);
        Toast.makeText(this, "Registro guardado satisfactoriamente!", Toast.LENGTH_SHORT).show();
    }
}
