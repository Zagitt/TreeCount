package com.dev.treecount.model;

import android.content.ContentValues;

public class ArbolTipo {
    private int idarbol;
    private String nombre_comun;
    private String nombre_cientifico;
    private int idParcela;

    public int getIdarbol() {
        return idarbol;
    }

    public void setIdarbol(int idarbol) {
        this.idarbol = idarbol;
    }

    public String getNombre_comun() {
        return nombre_comun;
    }

    public void setNombre_comun(String nombre_comun) {
        this.nombre_comun = nombre_comun;
    }

    public String getNombre_cientifico() {
        return nombre_cientifico;
    }

    public void setNombre_cientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public ArbolTipo() {
    }

    public ArbolTipo(int idarbol, String nombre_comun, String nombre_cientifico, int idParcela) {
        this.idarbol = idarbol;
        this.nombre_comun = nombre_comun;
        this.nombre_cientifico = nombre_cientifico;
        this.idParcela = idParcela;
    }
    public ArbolTipo(String nombre_comun, String nombre_cientifico, int idParcela) {
        this.nombre_comun = nombre_comun;
        this.nombre_cientifico = nombre_cientifico;
        this.idParcela = idParcela;
    }

    public ContentValues toContentValues () {
        ContentValues values = new ContentValues();
        values.put("idarbol", getIdarbol());
        values.put("nombre_comun", getNombre_comun());
        values.put("nombre_cientifico", getNombre_cientifico());
        values.put("idParcela", getIdParcela());
        return values;
    }
}
