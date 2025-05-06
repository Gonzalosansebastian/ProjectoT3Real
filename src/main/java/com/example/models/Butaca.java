package com.example.models;

public class Butaca {
    private int id;
    private String fila;
    private String columna;
    private String tipo;
    private boolean ocupada;

    public Butaca(int id, String fila, String columna, String tipo, boolean ocupada) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.ocupada = ocupada;
    }

    public int getId() { return id; }
    public String getFila() { return fila; }
    public String getColumna() { return columna; }
    public String getTipo() { return tipo; }
    public boolean isOcupada() { return ocupada; }

    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }
}

