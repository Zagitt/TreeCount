package com.dev.treecount.model;

import android.content.ContentValues;

/**
 * Created by Marlon on 15/08/2018.
 */

public class Person {
    private String nombre;
    private String dni;
    private int edad;
    private String biografia;
    private int idphoto;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person() {
    }

    public Person(String nombre, String dni, int edad, String biografia, int idphoto, int id) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.biografia = biografia;
        this.idphoto = idphoto;
        this.id = id;
    }

    public Person(String nombre, String dni, int edad, String biografia, int idphoto) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.biografia = biografia;
        this.idphoto = idphoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(int idphoto) {
        this.idphoto = idphoto;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put("nombre", this.nombre);
        values.put("dni", this.dni);
        values.put("edad", this.edad);
        values.put("biografia", this.biografia);
        values.put("idfoto", this.idphoto);
        return  values;
    }
}
