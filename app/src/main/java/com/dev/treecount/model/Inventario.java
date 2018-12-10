package com.dev.treecount.model;

import android.content.ContentValues;

public class Inventario {

    private int idInventario;
    private String nom_cientifico;
    private String nom_comun;
    private float lat;
    private float lon;
    private String dap;
    private int altura_fuste;
    private int altura_total;
    private int idParcela;

    public Inventario() {
    }

    public Inventario(String nom_cientifico, String nom_comun, float lat, float lon, String dap, int altura_fuste, int altura_total, int idParcela) {
        this.nom_cientifico = nom_cientifico;
        this.nom_comun = nom_comun;
        this.lat = lat;
        this.lon = lon;
        this.dap = dap;
        this.altura_fuste = altura_fuste;
        this.altura_total = altura_total;
        this.idParcela = idParcela;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getNom_cientifico() {
        return nom_cientifico;
    }

    public void setNom_cientifico(String nom_cientifico) {
        this.nom_cientifico = nom_cientifico;
    }

    public String getNom_comun() {
        return nom_comun;
    }

    public void setNom_comun(String nom_comun) {
        this.nom_comun = nom_comun;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getDap() {
        return dap;
    }

    public void setDap(String dap) {
        this.dap = dap;
    }

    public int getAltura_fuste() {
        return altura_fuste;
    }

    public void setAltura_fuste(int altura_fuste) {
        this.altura_fuste = altura_fuste;
    }

    public int getAltura_total() {
        return altura_total;
    }

    public void setAltura_total(int altura_total) {
        this.altura_total = altura_total;
    }

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }


    public ContentValues toContentValues () {
        ContentValues values = new ContentValues();
        values.put("nom_cientifico", getNom_cientifico());
        values.put("nom_comun", getNom_comun());
        values.put("lat", getLat());
        values.put("lon", getLon());
        values.put("dap", getDap());
        values.put("altura_fuste", getAltura_fuste());
        values.put("altura_total", getAltura_total());
        values.put("idParcela", getIdParcela());
        return values;
    }

}
