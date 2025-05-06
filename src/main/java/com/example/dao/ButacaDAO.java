package com.example.dao;

import com.example.ddbb.DatabaseConnection;
import com.example.models.Butaca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ButacaDAO {

    public static List<Butaca> obtenerButacasPorEspectaculo(int idEspectaculo) {
        List<Butaca> butacas = new ArrayList<>();
        String query = "SELECT b.id_butaca, b.fila, b.columna, b.tipo, " +
                "CASE WHEN EXISTS (" +
                "   SELECT 1 FROM RESERVAS r " +
                "   WHERE r.id_butaca = b.id_butaca AND r.id_espectaculo = ? AND r.estado = 'ocupada'" +
                ") THEN 1 ELSE 0 END AS ocupada " +
                "FROM BUTACAS b";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idEspectaculo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Butaca b = new Butaca(
                        rs.getInt("id_butaca"),
                        rs.getString("fila"),
                        rs.getString("columna"),
                        rs.getString("tipo"),
                        rs.getInt("ocupada") == 1
                );
                butacas.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return butacas;
    }
}

