package com.dev.treecount.model;

import android.content.ContentValues;

/**
 * Created by Marlon on 15/08/2018.
 */

public class Person {
    private String nombre;
    private String dni;
    private String cargo;
    private String apellido;
    private int idBrigada;
    private int idPersona;

    public Person() {
    }

    public Person(String nombre, String dni, String cargo, String apellido, int idBrigada, int idPersona) {
        this.nombre = nombre;
        this.dni = dni;
        this.cargo = cargo;
        this.apellido = apellido;
        this.idBrigada = idBrigada;
        this.idPersona = idPersona;
    }

    public Person(String nombre, String dni, String cargo, String apellido, int idBrigada) {
        this.nombre = nombre;
        this.dni = dni;
        this.cargo = cargo;
        this.apellido = apellido;
        this.idBrigada = idBrigada;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdBrigada() {
        return idBrigada;
    }

    public void setIdBrigada(int idBrigada) {
        this.idBrigada = idBrigada;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put("nombre", this.nombre);
        values.put("dni", this.dni);
        values.put("cargo", this.cargo);
        values.put("apellido", this.apellido);
        values.put("idBrigada", this.idBrigada);
        return  values;
    }
}
