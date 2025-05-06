package com.example.session;

import com.example.models.Espectaculo;

public class SesionEspectaculo {
    private static Espectaculo espectaculo;

    public static void setEspectaculo(Espectaculo e) {
        espectaculo = e;
    }

    public static Espectaculo getEspectaculo() {
        return espectaculo;
    }

    public static void limpiar() {
        espectaculo = null;
    }
}