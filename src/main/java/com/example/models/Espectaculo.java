package com.example.models;

import java.time.LocalDate;

public class Espectaculo {
    private int id;
    private String nombre;
    private LocalDate fecha;
    private double precioBase;
    private double precioVip;

    public Espectaculo(int id, String nombre, double precioBase, LocalDate fecha, double precioVip) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.fecha = fecha;
        this.precioVip = precioVip;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public double getPrecioVip() {
        return precioVip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void setPrecioVip(double precioVip) {
        this.precioVip = precioVip;
    }


}

