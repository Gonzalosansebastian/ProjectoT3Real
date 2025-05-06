package com.example.dao;

import com.example.ddbb.DatabaseConnection;
import com.example.models.Espectaculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EspectaculoDAO {

    public static List<Espectaculo> obtenerTodos() {
        List<Espectaculo> espectaculos = new ArrayList<>();
        String query = "SELECT * FROM ESPECTACULOS";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Espectaculo espectaculo = new Espectaculo(
                        rs.getInt("id_espectaculo"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_base"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("precio_vip")
                );
                espectaculos.add(espectaculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return espectaculos;
    }
}

