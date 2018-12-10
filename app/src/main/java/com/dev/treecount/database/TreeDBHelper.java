package com.dev.treecount.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.dev.treecount.model.ArbolTipo;
import com.dev.treecount.model.Inventario;
import com.dev.treecount.model.Parcela;
import com.dev.treecount.model.Person;
import com.dev.treecount.model.Persona;

import java.util.ArrayList;
import java.util.List;

public class TreeDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TreeCount.db";
    public static final int DATABASE_VERSION = 1;
    //private List<Producto> items = new ArrayList<>();

    public TreeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists parcela (" +
                   "idParcela integer primary key," +
                   "nombre varchar(45)," +
                   "ref_latitud decimal(11,8)," +
                   "ref_longitud decimal(11,8)," +
                   "p1_latitud decimal(11,8)," +
                   "p1_longitud decimal(11,8)," +
                   "p2_latitud decimal(11,8)," +
                   "p2_longitud decimal(11,8)," +
                   "p3_latitud decimal(11,8)," +
                   "p3_longitud decimal(11,8)," +
                   "p4_latitud decimal(11,8)," +
                   "p4_longitud decimal(11,8)," +
                   "departamento varchar(45)," +
                   "idBrigada integer," +
                   "brigadaNombre varchar(45))");

        db.execSQL("create table if not exists persona (" +
                "idPersona varchar(45) primary key," +
                "nombre varchar(45)," +
                "apellido varchar(45)," +
                "dni varchar(10)," +
                "cargo varchar(45)," +
                "idBrigada integer)");

        db.execSQL("create table if not exists brigada (" +
                   "idBrigada integer primary key," +
                   "nombre varchar(45))");

        db.execSQL("create table if not exists arbol (" +
                "idarbol integer primary key," +
                "nombre_comun varchar(45)," +
                "nombre_cientifico varchar(45)," +
                "idParcela integer)");

        db.execSQL("create table if not exists inventario (" +
                "idInventario integer primary key autoincrement," +
                "nom_cientifico varchar(45)," +
                "nom_comun varchar(45)," +
                "lat decimal(10,8)," +
                "lon decimal(10,8)," +
                "dap varchar(45)," +
                "altura_fuste decimal(10,4)," +
                "altura_total decimal(10,4)," +
                "idParcela integer)");

        onLoadInitialData(db);
    }

    private List<ArbolTipo> items = new ArrayList<>();

    private void onLoadInitialData(SQLiteDatabase db) {
        items.add(new ArbolTipo(1,"Pino Blanco", "Pinus albicaulis", 0));
        items.add(new ArbolTipo(2,"Caoba Hondureña", "Swietenia macrophylla", 0));
        items.add(new ArbolTipo(3,"Shihuahuaco", "Dipteryx micrantha", 0));
        items.add(new ArbolTipo(4,"Algarrobo", "Ceratonia siliqua", 0));

        for(ArbolTipo p: items){
            db.insert("arbol", null, p.toContentValues());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void limpiaTabla(String tableName){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tableName, null, null);
    }

    public void saveParcela(Parcela p){
        SQLiteDatabase db = getWritableDatabase();
        db.insert("parcela", null,p.toContentValues());
    }

    public void updateParcela(ContentValues values, int idParcela){
        SQLiteDatabase db = getWritableDatabase();
        db.update("parcela", values, "idParcela="+Integer.toString(idParcela),null);
    }

    public List<Parcela> getParcelas() {
        List<Parcela> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("parcela", null, null, null, null,null,"nombre");
        while (c.moveToNext()) {
            lista.add(new Parcela(
                    c.getInt(0),     // idParcela
                    c.getString(1),  // nombre
                    c.getFloat(2),   // ref_latitud
                    c.getFloat(3),   // ref_longitud
                    c.getFloat(4),   // p1_latitud
                    c.getFloat(5),   // p1_longitud
                    c.getFloat(6),   // p2_latitud
                    c.getFloat(7),   // p2_longitud
                    c.getFloat(8),   // p3_latitud
                    c.getFloat(9),   // p3_longitud
                    c.getFloat(10),  // p4_latitud
                    c.getFloat(11),  // p4_longitud
                    c.getString(12), // departamento
                    c.getInt(13),    // idBrigada
                    c.getString(14)  // brigadaNombre
            ));
        }
        return lista;
    }



    public List<Person> getPersonas(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("persona",
                null,   //COlumnas a listar
                null,   //Columnas en clausula WHERE
                null,   //Valores de las columnas de la clausula WHERE
                null,   //Group by - columnas
                null,   //Condicion de agrupamiento
                "nombre"
        );

        List<Person> lista = new ArrayList<>();
        while(c.moveToNext()){
            lista.add(new Person(
                    c.getString(1), //nombre
                    c.getString(3), //dni
                    c.getString(4),    //cargo
                    c.getString(2), //apellido
                    c.getInt(5),    //idBrigada
                    c.getInt(0)     //idPersona
            ));
        }
        return lista;
    }

    public List<ArbolTipo> getArbolesTipo(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("arbol",
                null,   //COlumnas a listar
                null,   //Columnas en clausula WHERE
                null,   //Valores de las columnas de la clausula WHERE
                null,   //Group by - columnas
                null,   //Condicion de agrupamiento
                "nombre_comun"
        );

        List<ArbolTipo> lista = new ArrayList<>();
        while(c.moveToNext()){
            lista.add(new ArbolTipo(
                    c.getInt(0), //idarbol
                    c.getString(1), //nombre_comun
                    c.getString(2),    //nombre_cientifico
                    c.getInt(3) //idParcela
            ));
        }
        return lista;
    }

    public List<Inventario> getInventario(int idParcela){
        SQLiteDatabase db = getReadableDatabase();
        String whereClause = "idParcela =?";
        String[] whereArgs = {""+idParcela};
        Cursor c = db.query("inventario",
                null,
                whereClause,
                whereArgs,   //Columnas en clausula WHERE
                   //Valores de las columnas de la clausula WHERE
                null,   //Group by - columnas
                null,   //Condicion de agrupamiento
                "idInventario"
        );

        List<Inventario> lista = new ArrayList<>();
        while(c.moveToNext()){
            lista.add(new Inventario(
                    c.getString(1), //nom_cientifico
                    c.getString(2), //nom_comun
                    c.getFloat(3),    //lat
                    c.getFloat(4), //lon
                    c.getString(5), //dap
                    c.getFloat(6), //altura_fuste
                    c.getFloat(7), //loaltura_totaln
                    c.getInt(8) //idParcela
            ));
        }
        return lista;
    }
    public void saveInventario(Inventario p) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert("inventario", null, p.toContentValues());
    }

    public void savePersona(Persona p){
        SQLiteDatabase db = getWritableDatabase();
        db.insert("persona", null, p.toContentValues());
    }

}