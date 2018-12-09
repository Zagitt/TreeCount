package com.dev.treecount.model;

public class Parcela {
    private int idParcela;
    private String nombre;
    private float refLatitud;
    private float refLongitud;
    private float p1Latitud;
    private float p1Longitud;
    private float p2Latitud;
    private float p2Longitud;
    private float p3Latitud;
    private float p3Longitud;
    private float p4Latitud;
    private float p4Longitud;
    private String brigadaNombre;

    public Parcela() {
    }

    public Parcela(int idParcela, String nombre, float refLatitud, float refLongitud, float p1Latitud, float p1Longitud, float p2Latitud, float p2Longitud, float p3Latitud, float p3Longitud, float p4Latitud, float p4Longitud) {
        this.idParcela = idParcela;
        this.nombre = nombre;
        this.refLatitud = refLatitud;
        this.refLongitud = refLongitud;
        this.p1Latitud = p1Latitud;
        this.p1Longitud = p1Longitud;
        this.p2Latitud = p2Latitud;
        this.p2Longitud = p2Longitud;
        this.p3Latitud = p3Latitud;
        this.p3Longitud = p3Longitud;
        this.p4Latitud = p4Latitud;
        this.p4Longitud = p4Longitud;
    }

    public int getIdParcela() { return idParcela; }

    public void setIdParcela(int idParcela) { this.idParcela = idParcela; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public float getRefLatitud() { return refLatitud; }

    public void setRefLatitud(float refLatitud) { this.refLatitud = refLatitud; }

    public float getRefLongitud() { return refLongitud; }

    public void setRefLongitud(float refLongitud) { this.refLongitud = refLongitud; }

    public float getP1Latitud() { return p1Latitud; }

    public void setP1Latitud(float p1Latitud) { this.p1Latitud = p1Latitud; }

    public float getP1Longitud() { return p1Longitud; }

    public void setP1Longitud(float p1Longitud) { this.p1Longitud = p1Longitud; }

    public float getP2Latitud() { return p2Latitud; }

    public void setP2Latitud(float p2Latitud) { this.p2Latitud = p2Latitud; }

    public float getP2Longitud() { return p2Longitud; }

    public void setP2Longitud(float p2Longitud) { this.p2Longitud = p2Longitud; }

    public float getP3Latitud() { return p3Latitud; }

    public void setP3Latitud(float p3Latitud) { this.p3Latitud = p3Latitud; }

    public float getP3Longitud() { return p3Longitud; }

    public void setP3Longitud(float p3Longitud) { this.p3Longitud = p3Longitud; }

    public float getP4Latitud() { return p4Latitud; }

    public void setP4Latitud(float p4Latitud) { this.p4Latitud = p4Latitud; }

    public float getP4Longitud() { return p4Longitud; }

    public void setP4Longitud(float p4Longitud) { this.p4Longitud = p4Longitud; }

    public String getBrigadaNombre() { return brigadaNombre; }

    public void setBrigadaNombre(String brigadaNombre) { this.brigadaNombre = brigadaNombre; }
}

/*
public class Person {
    private String nombre;
    private String dni;
    private int edad;
    private String biografia;
    private String urlfoto;

    public Person() {
    }

    public Person(String nombre, String dni, int edad, String biografia, String urlfoto) {
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.biografia = biografia;
        this.urlfoto = urlfoto;
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

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }
}
 */
