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
    private static double totalReserva;

    public static void setTotalReserva(double total) {
        totalReserva = total;
    }

    public static double getTotalReserva() {
        return totalReserva;
    }

}