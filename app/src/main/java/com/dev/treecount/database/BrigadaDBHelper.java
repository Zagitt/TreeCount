package com.dev.treecount.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import com.dev.treecount.R;
import com.dev.treecount.model.Person;

/**
 * Created by Marlon on 5/09/2018.
 */

public class BrigadaDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Customers.db";
    private List<Person> items = new ArrayList<>();

    public BrigadaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CLIENTE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre VARCHAR(120)," +
                "dni VARCHAR(8)," +
                "edad INTEGER," +
                "biografia VARCHAR(240)," +
                "idfoto INTEGER)");

        onLoadInitialData(db);
    }

    private void onLoadInitialData(SQLiteDatabase db) {
        items.add(new Person("Juan Perez", "40405566", 25, "Arquitecto de software, especialista en soluciones informáticas", R.drawable.face01));
        items.add(new Person("Marcos Perales", "30245211", 32, "Ingeniero de Sistemas con mas de 15 años en el sector público y provado", R.drawable.face02));
        items.add(new Person("Rosa Vargas", "45672111", 22, "Ingeniero civil con especialidad en Hidrologia", R.drawable.face03));
        items.add(new Person("Carmen Soto", "20345533", 40, "Licenciado en derecho con especialidad en familias", R.drawable.face04));
        items.add(new Person("Luis Palomino", "10782365", 42, "Profesor de literatura universal con especialidad en dramas", R.drawable.face05));
        items.add(new Person("Jorge Lopez", "10903211", 45, "Ingeniero electronico con especialidad en Domótica", R.drawable.face06));

        for(Person p: items){
            db.insert("CLIENTE", null, p.toContentValues());
        }
    }

    public void savePerson(Person p){
        SQLiteDatabase db = getWritableDatabase();
        db.insert("CLIENTE", null, p.toContentValues());
    }

    public int deletePerson(String personId) {
        return getWritableDatabase().delete("CLIENTE", "id=?",
                new String[]{personId});
    }

    public List<Person> getClientes(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("CLIENTE",
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
                    c.getString(1), //Nombre
                    c.getString(2), //DNI
                    c.getInt(3),    //Edad
                    c.getString(4), //Biografia
                    c.getInt(5),    //id Foto
                    c.getInt(0)     //ID
            ));
        }
        return lista;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
