package com.dev.treecount.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev.treecount.model.Parcela;

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
                   "idBrigada integer, " +
                   "brigadaNombre varchar(45))");

        db.execSQL("create table if not exists brigada (" +
                   "idBrigada integer primary key," +
                   "nombre varchar(45))");

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
        db.insert("parcela", null, p.toContentValues());
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
                    c.getFloat(11)   // p4_longitud
            ));
        }
        return lista;
    }


}