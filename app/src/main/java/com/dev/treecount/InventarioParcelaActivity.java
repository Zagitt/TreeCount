package com.dev.treecount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.treecount.adapter.InventarioAdapter;
import com.dev.treecount.database.TreeDBHelper;
import com.dev.treecount.model.Inventario;
import com.dev.treecount.model.Parcela;
import com.dev.treecount.services.GetHTTPTree;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class InventarioParcelaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView reciclador;
    Gson gson = new Gson();
    private Button btnNuevoArbol;
    private Button btnMapaArbol;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private EditText txtIdParcela;
    private EditText txtIdBrigada;
    private List<Inventario> inventario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_parcela);

        String data = getIntent().getExtras().getString("parcela");
        txtIdParcela = (EditText) findViewById(R.id.txtIdParcela);
        txtIdBrigada = (EditText) findViewById(R.id.txtIdBrigada);

        final Parcela parcela = gson.fromJson(data, Parcela.class);
        txtIdParcela.setText("ID: " + parcela.getIdParcela());
        txtIdBrigada.setText("ID: " + parcela.getIdBrigada());


        TreeDBHelper db = new TreeDBHelper(this);
        inventario = db.getInventario(parcela.getIdParcela());
        parcela.setInventarios(inventario);
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

        btnMapaArbol = (Button) findViewById(R.id.btnMapaArbol);
        btnMapaArbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent act = new Intent(view.getContext(), MapArbolActivity.class);
                act.putExtra("parcela", gson.toJson(parcela));
                view.getContext().startActivity(act);
            }
        });
        /***/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_parcela) {
            Intent act = new Intent(this, ParcelaActivity.class);
            startActivity(act);

            // Handle the camera action
        } else if (id == R.id.nav_brigada) {
            Intent act = new Intent(this, BrigadaActivity.class);
            startActivity(act);

        } else if (id == R.id.nav_estadisticas) {

        } else if (id == R.id.nav_cerrar_sesion) {
            LoginActivity login = new LoginActivity();
            login.cerrarSesion();
            Intent act = new Intent(this, LoginActivity.class);
            startActivity(act);


        } else if (id == R.id.nav_salir) {
            this.finishAffinity();


        } else if (id == R.id.nav_descargar) {
            GetHTTPTree ws = new GetHTTPTree(this);
            ws.execute();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}