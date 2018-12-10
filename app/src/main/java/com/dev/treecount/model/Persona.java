package com.dev.treecount.model;

import android.content.ContentValues;

public class Persona {
    private String idPersona;
    private String nombre;
    private String apellido;
    private String dni;
    private String cargo;
    private int idBrigada;

    public Persona() {
    }

    public Persona(String idPersona, String nombre, String apellido, String dni, String cargo, int idBrigada) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cargo = cargo;
        this.idBrigada = idBrigada;
    }

    public String getIdPersona() { return idPersona; }

    public void setIdPersona(String idPersona) { this.idPersona = idPersona; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public String getCargo() { return cargo; }

    public void setCargo(String cargo) { this.cargo = cargo; }

    public int getIdBrigada() { return idBrigada; }

    public void setIdBrigada(int idBrigada) { this.idBrigada = idBrigada; }

    public ContentValues toContentValues () {
        ContentValues values = new ContentValues();
        values.put("idPersona", getIdPersona());
        values.put("nombre", getNombre());
        values.put("apellido", getApellido());
        values.put("dni", getDni());
        values.put("cargo", getCargo());
        values.put("idBrigada", getIdBrigada());
        return values;
    }
}
